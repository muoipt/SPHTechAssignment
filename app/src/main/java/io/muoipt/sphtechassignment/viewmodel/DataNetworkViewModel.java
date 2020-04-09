package io.muoipt.sphtechassignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.muoipt.sphtechassignment.data.model.YearListWrapper;
import io.muoipt.sphtechassignment.data.source.remote.DataNetworkRepository;

public class DataNetworkViewModel extends ViewModel {

    DataNetworkRepository dataNetworkRepository;

    public DataNetworkViewModel(DataNetworkRepository dataNetworkRepository) {
        this.dataNetworkRepository = dataNetworkRepository;
    }

    public LiveData<YearListWrapper> getYearlyDataNetworkSent() {
        return dataNetworkRepository.getYearlyDataNetworkSent();
    }
}
