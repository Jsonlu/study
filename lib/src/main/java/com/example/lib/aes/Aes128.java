package com.example.lib.aes;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author:JsonLu
 * DateTime:2018/7/17 上午9:53
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Aes128 {


    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";


    public static String Encrypt(String src, String key) {
        if (key == null || key.length() != 16) {
            return null;
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);// "算法/模式/补码方式"0102030405060708
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(src.getBytes());

            return Base64.encodeToString(encrypted, Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }

    public static String DeEncrypt(String src, String key) {
        if (key == null || key.length() != 16) {
            return null;
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);// "算法/模式/补码方式"0102030405060708
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(Base64.decode(src, Base64.DEFAULT));

            return new String(encrypted);
        } catch (Exception e) {
            return null;
        }
    }
}
