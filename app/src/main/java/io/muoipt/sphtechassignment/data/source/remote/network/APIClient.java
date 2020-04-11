package io.muoipt.sphtechassignment.data.source.remote.network;

import android.content.Context;
import android.util.Log;

import io.muoipt.sphtechassignment.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.ConnectException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.muoipt.sphtechassignment.data.source.remote.response.DataRepoResponse;
import io.muoipt.sphtechassignment.data.source.remote.response.NetworkErrors;
import io.muoipt.sphtechassignment.data.source.remote.network.action.OnDataRepoResponse;
import io.muoipt.sphtechassignment.utils.Utils;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.muoipt.sphtechassignment.utils.Config.AUTHENTICATION_ERROR;
import static io.muoipt.sphtechassignment.utils.Config.INTERNAL_SERVER_ERROR;

@Singleton
public class APIClient {

    private static final String TAG = "APIClient";

    private APIInterface apiInterface;
    private Retrofit retrofit;

    Context context;

    @Inject
    public APIClient(Context context) {
        this.context = context;

        int cacheSize = 10 * 1024 * 1024;
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).addNetworkInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        }).addInterceptor(chain -> {
            Request request = chain.request();
            if (!Utils.checkInternetConnection(context)) {
                int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        this.apiInterface = retrofit.create(APIInterface.class);
    }

    public void getDataNetworkSent(OnDataRepoResponse onDataRepoResponse) {
        String resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f";
        Integer limit = null;

        Single<DataRepoResponse> datastoreResponse = apiInterface.getDataNetworkSent(resourceId, limit);
        datastoreResponse.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<DataRepoResponse>() {
                    @Override
                    public void onSuccess(DataRepoResponse dataRepoResponse) {
                        if (dataRepoResponse.getSuccess()) {
                            Log.d(TAG, "onSuccess: " + dataRepoResponse.getSuccess());

                            onDataRepoResponse.onSuccessDataRepoResponse(dataRepoResponse);

                        } else {
                            Log.e(TAG, "onSuccess: " + dataRepoResponse.getSuccess());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                        onDataRepoResponse.onErrorResponse(handleErrors(e));
                    }
                });
    }

    public String handleErrors(Throwable error) {
        if (error instanceof HttpException) {
            ResponseBody body = ((HttpException) error).response().errorBody();
            return handleError(body);
        } else if (error instanceof ConnectException) {
            return "Connection error";
        } else {
            return "Error occurred";
        }
    }

    private String handleError(ResponseBody errorBody) {
        Converter<ResponseBody, NetworkErrors> errorConverter = retrofit.responseBodyConverter(NetworkErrors.class, new Annotation[0]);
        NetworkErrors networkErrors = null;
        try {
            networkErrors = errorConverter.convert(errorBody); // Convert the error body into custom Error type.
            Log.e("Error occurred", networkErrors.status + " " + networkErrors.message);
            switch (networkErrors.status) {
                case AUTHENTICATION_ERROR:
                    return "Error occurred while authenticating";
                case INTERNAL_SERVER_ERROR:
                    return "Internal server error";
                default:
                    return "Unexpected error occurred";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return networkErrors.message;
    }

}

