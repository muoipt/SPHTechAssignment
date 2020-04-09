package io.muoipt.sphtechassignment.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {

    private final Context context;

    public ApplicationContextModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

}
