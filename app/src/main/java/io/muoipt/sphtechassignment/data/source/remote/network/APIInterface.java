package io.muoipt.sphtechassignment.data.source.remote.network;

import io.muoipt.sphtechassignment.data.source.remote.response.DataRepoResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("action/datastore_search")
    Single<DataRepoResponse> getDataNetworkSent(@Query("resource_id") String resourceId, @Query("limit") Integer limit);

}


