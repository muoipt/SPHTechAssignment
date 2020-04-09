package io.muoipt.sphtechassignment.data.source.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.muoipt.sphtechassignment.data.model.Quarter;
import io.muoipt.sphtechassignment.data.model.Year;
import io.muoipt.sphtechassignment.data.model.YearListWrapper;
import io.muoipt.sphtechassignment.data.source.remote.response.DataRepoResponse;
import io.muoipt.sphtechassignment.data.source.remote.response.QuarterResponse;
import io.muoipt.sphtechassignment.data.source.remote.network.APIClient;
import io.muoipt.sphtechassignment.data.source.remote.network.action.OnDataRepoResponse;

public class DataNetworkRepository {

    private static final String TAG = "DataNetworkRepository";

    APIClient apiClient;
    private List<Year> yearList = new ArrayList<>();


    private MutableLiveData<YearListWrapper> mutableYearListWrapper = new MutableLiveData<>();

    @Inject
    public DataNetworkRepository(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    public LiveData<YearListWrapper> getYearlyDataNetworkSent() {
        apiClient.getDataNetworkSent(new OnDataRepoResponse() {

            @Override
            public void onErrorResponse(String error) {
                mutableYearListWrapper.setValue(new YearListWrapper(error));
            }

            @Override
            public void onSuccessDatastoreResponse(DataRepoResponse dataRepoResponse) {

                List<Quarter> quarterList = new ArrayList<>();

                int yearIndex = 2008;
                int yearEndIndex = 2018;

                for (int i = 0; i < dataRepoResponse.getResult().getRecords().size(); i++) {

                    QuarterResponse q = dataRepoResponse.getResult().getRecords().get(i);

                    Log.d(TAG, "Quarter: " + q.get_id() + " : " + q.getQuarter());

                    String quarterInfo[] = q.getQuarter().split("-");
                    Integer year = Integer.parseInt(quarterInfo[0]);
                    String quarterName = quarterInfo[1];

                    if (year >= 2008 && year <= yearEndIndex) {

                        if (yearIndex == year) {
                            quarterList.add(new Quarter(q.get_id(), q.getVolume_of_mobile_data(), year, quarterName));
                        } else {
                            yearList.add(new Year(yearIndex, quarterList));

                            quarterList = new ArrayList<>();

                            yearIndex++;

                            quarterList.add(new Quarter(q.get_id(), q.getVolume_of_mobile_data(), year, quarterName));
                        }

                        if (i == dataRepoResponse.getResult().getRecords().size() - 1) {
                            yearList.add(new Year(year, quarterList));
                        }

                    }

                }
                mutableYearListWrapper.setValue(new YearListWrapper(yearList));
            }
        });

        return mutableYearListWrapper;

    }

}
