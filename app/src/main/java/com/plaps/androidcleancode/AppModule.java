package com.plaps.androidcleancode;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Class created by nhut.lv on 4/18/2018.
 *
 * @author nhut.lv.
 * @since 18-Apr-2018
 */
@Module
public class AppModule {
    public Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }
}
