package com.test;

import com.example.lib.aes.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Author:JsonLu
 * DateTime:2018/7/31 上午9:39
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class Rd {

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            System.out.println("security provider BC not found");
            Security.addProvider(new BouncyCastleProvider());
        }
    }


    /**
     * 公钥解密
     *
     * @param content
     * @param pubKey
     * @return
     */
    public static String decrypt(String content, String pubKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(pubKey, Base64.DEFAULT));
        try {
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyf.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPPadding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] inputByte = Base64.decode(content, Base64.NO_WRAP);
            inputByte = cipher.doFinal(inputByte);
            return new String(inputByte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥加密
     *
     * @param content
     * @param priKey
     * @return
     */
    public static String encrypt(String content, String priKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(priKey, Base64.DEFAULT));
        try {
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyf.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPPadding");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] inputByte = content.getBytes();
            inputByte = cipher.doFinal(inputByte);
            return Base64.encodeToString(inputByte, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

