package com.jolly.pay.base.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

/**
 * Author:JsonLu
 * DateTime:2019/12/18 17:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class AesGcm {

    Key tokey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed("123456".getBytes(StandardCharsets.UTF_8));
        generator.init(secureRandom);
        Key key = generator.generateKey();
        return key;
    }
}
