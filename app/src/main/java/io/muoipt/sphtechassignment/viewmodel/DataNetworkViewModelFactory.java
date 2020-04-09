package io.muoipt.sphtechassignment.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.muoipt.sphtechassignment.data.source.remote.DataNetworkRepository;

public class DataNetworkViewModelFactory implements ViewModelProvider.Factory {

    private static final String TAG = "DataNetworkViewModelFactory";

    private final DataNetworkRepository dataNetworkRepository;

    public DataNetworkViewModelFactory(DataNetworkRepository dataNetworkRepository) {
        this.dataNetworkRepository = dataNetworkRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DataNetworkViewModel.class)) {
            return (T) new DataNetworkViewModel(dataNetworkRepository);
        } else {
            Log.e(TAG, "Invalid ViewModel Type");
            throw new IllegalArgumentException("Invalid ViewModel Type");
        }

    }
}