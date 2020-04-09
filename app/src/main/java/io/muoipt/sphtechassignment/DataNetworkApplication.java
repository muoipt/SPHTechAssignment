package io.muoipt.sphtechassignment;

import android.app.Application;

import io.muoipt.sphtechassignment.di.component.ApplicationComponent;
import io.muoipt.sphtechassignment.di.component.DaggerApplicationComponent;
import io.muoipt.sphtechassignment.di.module.ApplicationContextModule;
import io.muoipt.sphtechassignment.di.module.DataModule;


public class DataNetworkApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .dataModule(new DataModule())
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

