package com.colin.colin1.hotUpdate.exception;

import java.net.MalformedURLException;

/**
 * Created by puxiang on 2018/2/6.
 */

public class MalformedDataException extends RuntimeException {
    public MalformedDataException(String path, Throwable cause) {
        super("Unable to parse contents of " + path + ", the file may be corrupted.", cause);
    }
    public MalformedDataException(String url, MalformedURLException cause) {
        super("The package has an invalid downloadUrl: " + url, cause);
    }
}
