package com.test;


import com.example.lib.aes.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author:JsonLu
 * DateTime:2019/5/21 14:49
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Ae {

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            System.out.println("security provider BC not found");
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 密钥算法
     * java6支持56位密钥，bouncycastle支持64位
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法/工作模式/填充方式
     * JAVA6 支持PKCS5PADDING填充方式
     * Bouncy castle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     */
    private static Key toKey(byte[] key) {

        // 实例化DES密钥
        // 生成密钥safe
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密后的数据
     */
    private static byte[] encrypt(byte[] data, byte[] key, byte iv[]) throws Exception {


        // 还原密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
//        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k, new IvParameterSpec(iv));
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 加密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密后的数据
     */
    private static byte[] decrypt(byte[] data, byte[] key, byte iv[]) throws Exception {


        // 还原密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
//        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        // 初始化，设置为加密模式
        cipher.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));
        // 执行操作
        return cipher.doFinal(data);
    }


    /**
     * 加密
     *
     * @param newStr，本地加密上传
     * @return 密文
     * @throws Exception
     * @author Guocg
     */
    public static String encrypt(String newStr, String key, byte[] iv) throws Exception {

        return Base64.encodeToString(Ae.encrypt(newStr.getBytes(), key.getBytes(), iv), Base64.NO_WRAP);
    }

    /**
     * 解密
     *
     * @param newStr，本地加密上传
     * @return 密文
     * @throws Exception
     * @author Guocg
     */
    public static String decrypt(String newStr, String key, byte[] iv) throws Exception {

        return Base64.encodeToString(Ae.encrypt(newStr.getBytes(), key.getBytes(), iv), Base64.NO_WRAP);
    }

}
