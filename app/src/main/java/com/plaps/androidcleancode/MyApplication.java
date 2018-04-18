package com.plaps.androidcleancode;

import android.app.Application;

import com.plaps.androidcleancode.deps.DaggerDeps;
import com.plaps.androidcleancode.deps.Deps;
import com.plaps.androidcleancode.networking.NetworkModule;

import java.io.File;

/**
 * Class created by nhut.lv on 4/18/2018.
 *
 * @author nhut.lv.
 * @since 4/18/2018
 */
public class MyApplication extends Application {

    private Deps networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        networkComponent = DaggerDeps.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(cacheFile))
                .build();
    }

    public Deps getNetworkComponent() {
        return networkComponent;
    }
}
