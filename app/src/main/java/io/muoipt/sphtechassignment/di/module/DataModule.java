package io.muoipt.sphtechassignment.di.module;

import androidx.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.muoipt.sphtechassignment.data.source.remote.network.APIClient;
import io.muoipt.sphtechassignment.data.source.remote.DataNetworkRepository;
import io.muoipt.sphtechassignment.viewmodel.DataNetworkViewModelFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    DataNetworkRepository provideDataNetworkRepository(APIClient apiClient) {
        return new DataNetworkRepository(apiClient);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(DataNetworkRepository dataNetworkRepository) {
        return new DataNetworkViewModelFactory(dataNetworkRepository);
    }

}
