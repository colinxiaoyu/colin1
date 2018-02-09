package com.colin.colin1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.colin.colin1.rootView.preloadreact.PreLoadReactActivity;



public class MyReactActivity1 extends PreLoadReactActivity {

    @Override
    protected String getMainComponentName() {
        return "colin1";
    }

//    private ReactRootView mReactRootView;
//    private ReactInstanceManager mReactInstanceManager;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = getIntent();
//        int type = intent.getIntExtra("type", 1);
//        Bundle bundle = new Bundle();
//        bundle.putInt("type", type);
//
//
//
////        mReactRootView = new ReactRootView(this);
////        mReactInstanceManager = ReactInstanceManager.builder()
////                .setApplication(getApplication())
////                .setBundleAssetName("index.android.bundle")
////                .setJSMainModulePath("index/index")
////                .addPackage(new MainReactPackage())
////                .setUseDeveloperSupport(BuildConfig.DEBUG)
////                .setInitialLifecycleState(LifecycleState.RESUMED)
////                .build();
////        mReactRootView.startReactApplication(mReactInstanceManager, "colin1", bundle);
//
//        setContentView(mReactRootView);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onHostResume(this, this);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onHostPause();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onHostDestroy(this);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        if (mReactInstanceManager != null) {
//            mReactInstanceManager.onBackPressed();
//            mReactInstanceManager = null;
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public void invokeDefaultOnBackPressed() {
//        super.onBackPressed();
//
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (BuildConfig.DEBUG && keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
//            mReactInstanceManager.showDevOptionsDialog();
//            return true;
//        }
//        return super.onKeyUp(keyCode, event);
//    }
}