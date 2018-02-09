package com.colin.colin1.hotUpdate;

import android.content.Context;

import com.colin.colin1.hotUpdate.exception.UnknownException;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import org.json.JSONObject;

import java.util.List;


/**
 * Created by puxiang on 2018/2/6.
 */

public class HotUpdate implements ReactPackage {

    private static HotUpdate mCurrentInstance;

    private String mAssetsBundleFileName;

    private UpdateManager mUpdateManager;

    //是否是正在运行的bundle版本
    private static boolean sIsRunningBinaryVersion = false;
    //是否更新完成
    private boolean mDidUpdate = false;

    private  boolean mIsDebugMode;



    private Context mContext;

    private static String sAppVersion = null;

    public boolean didUpdate() {
        return mDidUpdate;
    }



    public static String getJSBundleFile() {
        return HotUpdate.getJSBundleFile(Constants.DEFAULT_JS_BUNDLE_NAME);
    }

    public static String getJSBundleFile(String assetsBundleFileName) {
        if (mCurrentInstance == null) {
            throw new UnknownException("A CodePush instance has not been created yet. Have you added it to your app's list of ReactPackages?");
        }

        return mCurrentInstance.getJSBundleFileInternal(assetsBundleFileName);
    }

    boolean isRunningBinaryVersion() {
        return sIsRunningBinaryVersion;
    }


    public String getJSBundleFileInternal(String assetsBundleFileName) {
        this.mAssetsBundleFileName = assetsBundleFileName;
        String binaryJsBundleUrl = Constants.ASSETS_BUNDLE_PREFIX + assetsBundleFileName;
        // 拿到assets目录下的 bundleUrl
        String packageFilePath = mUpdateManager.getCurrentPackageBundlePath(this.mAssetsBundleFileName);
        if (packageFilePath == null) {
            //一般情况下，第一次加载，木有热更新走这一步
            Utils.logBundleUrl(binaryJsBundleUrl);
            sIsRunningBinaryVersion = true;
            return binaryJsBundleUrl;//返回 assets下的bundle路径
        }

        //返回app私有文件路径  /CodePush/CURRENT_PACKAGE_KEY/app.json 的JSONObject 对象
        JSONObject packageMetadata = mUpdateManager.getCurrentPackage();
        if (isPackageBundleLatest(packageMetadata)) {//是最新bundle包
            Utils.logBundleUrl(packageFilePath);
            sIsRunningBinaryVersion = false;
            return packageFilePath;
        } else {
            // The binary version is newer.
            this.mDidUpdate = false;
            if (!this.mIsDebugMode || hasBinaryVersionChanged(packageMetadata)) {
//                this.clearUpdates();
            }

            Utils.logBundleUrl(binaryJsBundleUrl);
            sIsRunningBinaryVersion = true;
            return binaryJsBundleUrl;
        }
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return null;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return null;
    }

    //判断是否是最新的 bundle
    private boolean isPackageBundleLatest(JSONObject packageMetadata) {
        try {
            Long binaryModifiedDateDuringPackageInstall = null;
            String binaryModifiedDateDuringPackageInstallString = packageMetadata.optString(Constants.BINARY_MODIFIED_TIME_KEY, null);
            if (binaryModifiedDateDuringPackageInstallString != null) {
                binaryModifiedDateDuringPackageInstall = Long.parseLong(binaryModifiedDateDuringPackageInstallString);
            }
            String packageAppVersion = packageMetadata.optString("appVersion", null);
            long binaryResourcesModifiedTime = this.getBinaryResourcesModifiedTime();
            return binaryModifiedDateDuringPackageInstall != null &&
                    binaryModifiedDateDuringPackageInstall == binaryResourcesModifiedTime &&//取保apk发行的时间与app.json中的时间一直
                    sAppVersion.equals(packageAppVersion);//当前版本号与app.json中版本号一直
        } catch (NumberFormatException e) {
            throw new UnknownException("Error in reading binary modified date from package metadata", e);
        }
    }

    long getBinaryResourcesModifiedTime() {
        try {
            String packageName = this.mContext.getPackageName();
            int codePushApkBuildTimeId = this.mContext.getResources().getIdentifier(Constants.CODE_PUSH_APK_BUILD_TIME_KEY, "string", packageName);
            // replace double quotes needed for correct restoration of long value from strings.xml
            // https://github.com/Microsoft/cordova-plugin-code-push/issues/264
            String codePushApkBuildTime = this.mContext.getResources().getString(codePushApkBuildTimeId).replaceAll("\"", "");
            return Long.parseLong(codePushApkBuildTime);
        } catch (Exception e) {
            throw new UnknownException("Error in getting binary resources modified time", e);
        }
    }
    //是否存在版本变动
    private boolean hasBinaryVersionChanged(JSONObject packageMetadata) {
        String packageAppVersion = packageMetadata.optString("appVersion", null);
        return !sAppVersion.equals(packageAppVersion);
    }
}
