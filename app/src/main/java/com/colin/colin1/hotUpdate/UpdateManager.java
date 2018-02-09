package com.colin.colin1.hotUpdate;

import com.colin.colin1.hotUpdate.exception.UnknownException;

import org.json.JSONObject;

import java.io.IOException;



/**
 * Created by puxiang on 2018/2/6.
 */

public class UpdateManager  {

    //app私有文件的路径 /data/data/xxx包名/files 的绝对路径
    private String mDocumentsDirectory;

    public UpdateManager(String documentsDirectory) {
        mDocumentsDirectory = documentsDirectory;
    }

    public String getCurrentPackageBundlePath(String bundleFileName) {
        //返回 /CodePush/CURRENT_PACKAGE_KEY的字典信息
        String packageFolder = getCurrentPackageFolderPath();
        if (packageFolder == null) {
            return null;
        }

        //返回app私有文件路径  /CodePush/CURRENT_PACKAGE_KEY/app.json 的JSONObject 对象
        JSONObject currentPackage = getCurrentPackage();
        if (currentPackage == null) {
            return null;
        }

        //返回app私有文件路径  /CodePush/CURRENT_PACKAGE_KEY/app.json 的JSONObject 对象中RELATIVE_BUNDLE_PATH_KEY的值
        String relativeBundlePath = currentPackage.optString(Constants.RELATIVE_BUNDLE_PATH_KEY, null);
        if (relativeBundlePath == null) {
            //app.json中的RELATIVE_BUNDLE_PATH_KEY 不存在， 直接从assets目录下读取
            return Utils.appendPathComponent(packageFolder, bundleFileName);
        } else {
            //从/CodePush/CURRENT_PACKAGE_KEY/app.json中的RELATIVE_BUNDLE_PATH_KEY对应值的路径
            return Utils.appendPathComponent(packageFolder, relativeBundlePath);
        }
    }

    //app私有文件路径获取 读取/CodePush/codepush.json文件 获得CURRENT_PACKAGE_KEY 关键字的信息
    public String getCurrentPackageFolderPath() {
        JSONObject info = getCurrentPackageInfo();
        String packageHash = info.optString(Constants.CURRENT_PACKAGE_KEY, null);
        if (packageHash == null) {
            return null;
        }
        //返回 /CodePush/CURRENT_PACKAGE_KEY的字典信息
        return getPackageFolderPath(packageHash);
    }

    public JSONObject getCurrentPackageInfo() {
        String statusFilePath = getStatusFilePath();
        //如果文件不存在，创建一个空的 JSONObject
        if (!FileUtils.fileAtPathExists(statusFilePath)) {
            return new JSONObject();
        }

        try {
            //app私有文件路径获取 /CodePush/codepush.json 返回的 JSONObject
            return Utils.getJsonObjectFromFile(statusFilePath);
        } catch (IOException e) {
            // Should not happen.
            throw new UnknownException("Error getting current package info", e);
        }
    }

    //app私有文件路径获取 /CodePush/codepush.json文件
    private String getStatusFilePath() {
        return Utils.appendPathComponent(getCodePushPath(), Constants.STATUS_FILE);
    }

    //app私有文件路径获取 /CodePush文件夹
    private String getCodePushPath() {
        return Utils.appendPathComponent(getDocumentsDirectory(), Constants.CODE_PUSH_FOLDER_PREFIX);
    }

    //app私有文件路径
    private String getDocumentsDirectory() {
        return mDocumentsDirectory;
    }

    public String getPackageFolderPath(String packageHash) {
        return Utils.appendPathComponent(getCodePushPath(), packageHash);
    }

    //返回app私有文件路径  /CodePush/CURRENT_PACKAGE_KEY/app.json 的JSONObject 对象
    public JSONObject getCurrentPackage() {
        String packageHash = getCurrentPackageHash();
        if (packageHash == null) {
            return null;
        }

        return getPackage(packageHash);
    }

    //app私有文件路径获取 /CodePush/codepush.json文件 中CURRENT_PACKAGE_KEY字典的值
    public String getCurrentPackageHash() {
        JSONObject info = getCurrentPackageInfo();
        return info.optString(Constants.CURRENT_PACKAGE_KEY, null);
    }

    //返回app私有文件路径  /CodePush/CURRENT_PACKAGE_KEY/app.json 的JSONObject 对象
    public JSONObject getPackage(String packageHash) {
        String folderPath = getPackageFolderPath(packageHash);
        String packageFilePath = Utils.appendPathComponent(folderPath, Constants.PACKAGE_FILE_NAME);
        try {
            return Utils.getJsonObjectFromFile(packageFilePath);
        } catch (IOException e) {
            return null;
        }
    }




}
