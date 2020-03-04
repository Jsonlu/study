package top.jsonlu.baselibrary.croypt;


import android.util.Base64;

import java.security.Key;

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
public class Aes {

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
        // 生成密钥
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
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
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        // 初始化，设置为加密模式
        cipher.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param newStr，本地解密
     * @return 明文
     * @throws Exception
     */
    public static String decrypt(String newStr, String key, byte[] iv) throws Exception {

        return new String(Aes.decrypt(Base64.decode(newStr, Base64.NO_WRAP), key.getBytes(), iv));
    }

}
