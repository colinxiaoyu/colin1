package com.colin.colin1.hotUpdate;

import android.util.Log;

import com.colin.colin1.hotUpdate.exception.MalformedDataException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;


/**
 * Created by puxiang on 2018/2/6.
 */

public class Utils {
    public static void log(String message) {
        Log.d(Constants.REACT_NATIVE_LOG_TAG, "[CodePush] " + message);
    }

    public static String appendPathComponent(String basePath, String appendPathComponent) {
        return new File(basePath, appendPathComponent).getAbsolutePath();
    }

    public static void logBundleUrl(String path) {
        log("Loading JS bundle from \"" + path + "\"");
    }

    // 从app私有文件路径获取 /CodePush/codepush.json文件
    public static JSONObject getJsonObjectFromFile(String filePath) throws IOException {
        String content = FileUtils.readFileToString(filePath);
        try {
            return new JSONObject(content);
        } catch (JSONException jsonException) {
            // Should not happen
            throw new MalformedDataException(filePath, jsonException);
        }
    }
}
