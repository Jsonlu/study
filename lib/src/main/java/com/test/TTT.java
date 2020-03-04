package com.test;


import com.example.lib.aes.Aes256;
import com.example.lib.aes.Base64;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Author:JsonLu
 * DateTime:2019/12/23 15:30
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class TTT {

    public static void main(String... args) throws Exception {
//        TTT t = new TTT();
//        t.test();
//        byte[] aa = Base64.decode("O7jQrfFdIvUMwN3gDmfA5i4q3sMEHB7arnQz7MyyW5Hf0zjVuoEabbTOl0wy15/rll5gc4RbKXHA\n" +
//                "uhGTssJ4hz2Ay/AuwAAlcIOK6uSDh/BnCpJHjJcFPSyw1BGYkyWn3C3CZPO3OCS1w5ynatlzjyLG\n" +
//                "7IpZ7oF69JN68CXHhnsXyxcGZ8oEJmXuZr3XhZ8DgT/Xt9sKEr+G8qKTk4/PUXS0RT0MmgxrwMi4\n" +
//                "TM2DLGu6L4UxH+MRsKkm6qiK2ugJ04eW72l+UCExXyYZ1xku5jhMZm4+jwyQyHzSE3Y352enJbmS\n" +
//                "gKP3Iw==",Base64.DEFAULT);
//        System.out.println(new String(aa));

//        String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK2x88VK3UK2BgkFO4m+xv0W6gFA6m7X6AHpCva+cKx9Q/AHoEdgEzPWhmPeVNcVKS6xMKeLsBFIXzVSb6mzREtGd2FujaoQDMwu7HX75lp4v7M9OuaLAJ1nZ0REpr95BhwLUotmb0vNfqeK+ux6fkRCSsJdV8h+7xhoNbnHa97tAgMBAAECgYAW+yd0oA4cSHcN9HpHJE2xqLl3Rv59geV5czr3IIVJ3R0DAg9LwkNoJWcMiZsSBgO34Mu+LzYxYzDtniWV/1j2oM65RNDZgEUsN5FpyQnArT5ElzZjyulxsuyVbiH4Q8Pu2SqfZeIxz+vFqQKxkvbPaZtmZaMt0vH1ZW6zSYJb5QJBAN7OLNFW7xQB07p1if94a8Vrw3awsaPySNktZ2PxBidMu0LStR5OPEgpKepBXwVQhx0KjfKCtDV4NUquh4/yvQMCQQDHkrTKQ3Id091rPAqHnict8QHyraoaTVhpjJt2LTHQMqSvTT8L4iAFa4ccgdqGUhJHkBiELRr0pjQd6LihxNlPAkBZZs9y/HgYRhdoNSu3V+GeqPwYjxPonhp/Eonu/1KhyFdHm/bS/z+PEP1hovN21XYxv/9vNOUMo3x2yAtbRmJzAkAzJnG5yXGNHYkJe/kC61EFYFjsdwA6/4nie4w4zLqQrvowh+7Zv9oPKZEWiF2WwaA3wUHXVqwq+hIvfD8eDxLXAkEAgvXX12sWstVTMUvwYZVQxtOq38px0P5Wv6CPDBuas9+KdvKXqqh4xKFKjGCJmxEj+3kRLMQPyha1fSyb8cFtuQ==";
//        String content = "asdff";
//        String aa = Rd.encrypt(content, key);
//        System.out.println(aa);
        /**
         * password： JollyPayAESK!Q2w100024840005Joll
         iv： w1y7r2tu5o4fydy4
         加后： rS79/Hx3uFMCak0+pcq0Rg==
         */
        String a = Ae.encrypt("01/22","JollyPayAESK!Q2w100024840005Joll","w1y7r2tu5o4fydy4".getBytes());
        System.out.println(a);

        String data = "rS79/Hx3uFMCak0+pcq0Rg==";
        String key = "";


    }

    void test() {
        AesGcmEncryption aesGcmEncryption = new AesGcmEncryption();
        byte[] rawData = "asdf".getBytes();
        byte[] keys = getKey();
        byte[] associatedData = "a".getBytes();
        try {
            byte[] a = aesGcmEncryption.encrypt(keys, rawData, associatedData);
            System.out.println(Base64.encodeToString(a, Base64.NO_WRAP));
            a = aesGcmEncryption.decrypt(keys, a, associatedData);
            System.out.println(new String(a));
        } catch (AuthenticatedEncryptionException e) {
            e.printStackTrace();
        }
    }

    byte[] getKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32];
        key = Base64.decode("hmM4IfFgN52x71/7wtAHo47X/M/MOFyMpIGeBcxNRao=", Base64.NO_WRAP);
//        secureRandom.nextBytes(key);
        System.out.println(Base64.encodeToString(key, Base64.NO_WRAP));
        Key secretKey = toKey(key);
        return secretKey.getEncoded();
    }

    public byte[] t(byte[] plainText) throws Exception {

        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        Key secretKey = toKey(key);

        byte[] iv = new byte[12]; //NEVER REUSE THIS IV WITH SAME KEY
        secureRandom.nextBytes(iv);


        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        byte[] associatedData = null;
        if (associatedData != null) {
            cipher.updateAAD(associatedData);
        }

        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv); //128 bit auth tag length
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        byte[] cipherText = cipher.doFinal(plainText);

        return cipherText;
    }

    private Key toKey(byte[] key) {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }
}
