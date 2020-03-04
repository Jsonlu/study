package com.example.log;

/**
 * Author:JsonLu
 * DateTime:2019/12/23 15:45
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class AuthenticatedEncryptionException extends Exception {

    public AuthenticatedEncryptionException(String message) {
        super(message);
    }

    public AuthenticatedEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
