package com.jolly.pay.base.security;

import com.example.lib.aes.Base64;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author:JsonLu
 * DateTime:2019/10/10 15:20
 * Email:jsonlu@qq.com
 * Desc:密钥无关，基于口令的加密算法
 **/
public final class IVRandom {

    private static final String password = "password";


    /**
     * 加密
     *
     * @param data
     * @return
     */
    public static Result ENCRYPT_MODE(String data) {
        try {
            int iterationCount = 1000;
            int keyLength = 256;
            int saltLength = keyLength / 8; // same size as key output
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[saltLength];
            random.nextBytes(salt);

            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
            SecretKey key = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            random.nextBytes(iv);
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
            Result result = new Result();
            result.data = Base64.encodeToString(cipher.doFinal(data.getBytes()), Base64.DEFAULT);
            result.iv = Base64.encodeToString(iv, Base64.DEFAULT);
            result.salt = Base64.encodeToString(salt, Base64.DEFAULT);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param data
     * @return
     */
    public static String DECRYPT_MODE(Result data) {
        try {
            byte[] salt = Base64.decode(data.salt, Base64.DEFAULT);
            byte[] iv = Base64.decode(data.iv, Base64.DEFAULT);
            int iterationCount = 1000;
            int keyLength = 256;
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();
            SecretKey key = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
            return new String(cipher.doFinal(Base64.decode(data.data, Base64.DEFAULT)));
        } catch (Exception e) {
            return null;
        }

    }

    public static class Result {
        String iv;
        String salt;
        String data;
    }

}
