package com.example.lib.lsb;

/**
 * Author:JsonLu
 * DateTime:2019/9/24 15:04
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class SteganographyException extends Exception {

    private static final long serialVersionUID = 9031373046571531684L;

    public SteganographyException() {
        super();
    }

    public SteganographyException(String message) {
        super(message);
    }

    public SteganographyException(Throwable throwable) {
        super(throwable);
    }

    public SteganographyException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

