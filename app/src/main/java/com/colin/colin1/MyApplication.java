package com.colin.colin1;

import android.app.Application;
import android.support.annotation.Nullable;

import com.colin.colin1.initReactPackage.InitReactPackage;
import com.colin.colin1.model.InitState;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by puxiang on 2018/2/8.
 */

public class MyApplication extends Application implements ReactApplication {

    private static MyApplication instance;

    private InitState initState = new InitState();

    public InitState getInitState() {
        return initState;
    }

    public void setInitState(InitState initState) {
        this.initState = initState;
    }

    ReactNativeHost reactNativeHost = new ReactNativeHost(this) {

        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new InitReactPackage()
            );
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }



    @Override
    public ReactNativeHost getReactNativeHost() {
        return reactNativeHost;
    }

    public String getAppPackageName() {
        return this.getPackageName();
    }

    /**
     * 获取Application实例
     */
    public static MyApplication getInstance() {
        return instance;
    }


}
