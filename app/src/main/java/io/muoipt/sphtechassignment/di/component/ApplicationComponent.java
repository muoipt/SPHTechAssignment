package io.muoipt.sphtechassignment.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.muoipt.sphtechassignment.di.module.ApplicationContextModule;
import io.muoipt.sphtechassignment.di.module.DataModule;
import io.muoipt.sphtechassignment.data.source.remote.network.APIClient;
import io.muoipt.sphtechassignment.data.source.remote.DataNetworkRepository;
import io.muoipt.sphtechassignment.view.MainActivity;

@Singleton
@Component(modules = {ApplicationContextModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(DataNetworkRepository dataNetworkRepository);

    APIClient apiClient();

    Context context();

}
