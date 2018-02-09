package com.colin.colin1.initReactPackage;

import com.colin.colin1.MyApplication;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by puxiang on 2018/2/9.
 */

public class InitReactMoudle extends ReactContextBaseJavaModule {

    ReactApplicationContext reactContext;



    public InitReactMoudle(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "InitReact";
    }

    @ReactMethod
    public void getInitState(final Promise promise){
        try{
            int type = MyApplication.getInstance().getInitState().getType();
            promise.resolve(type);
        }catch (Exception e){
            promise.reject(e);
        }

    }
}
