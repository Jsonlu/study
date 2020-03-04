package com.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author:JsonLu
 * DateTime:2019/12/23 15:44
 * Email:jsonlu@qq.com
 * Desc:
 **/
public interface AuthenticatedEncryption {
    @Retention(RetentionPolicy.SOURCE)
    @interface KeyStrength {
    }

    int STRENGTH_HIGH = 0;

    int STRENGTH_VERY_HIGH = 1;

    /**
     * 加密 & 身份验证
     *
     * @param rawEncryptionKey 密钥
     * @param rawData          密文
     * @param associatedData   身份标志
     * @return 密文
     * @throws AuthenticatedEncryptionException 加密失败
     */
    byte[] encrypt(byte[] rawEncryptionKey, byte[] rawData, byte[] associatedData) throws AuthenticatedEncryptionException;

    /**
     * 解密 & 身份验证
     *
     * @param rawEncryptionKey 密钥
     * @param encryptedData    密文
     * @param associatedData   验证身份标志
     * @return decrypt，解密数据
     * @throws AuthenticatedEncryptionException 解密失败
     */
    byte[] decrypt(byte[] rawEncryptionKey, byte[] encryptedData, byte[] associatedData) throws AuthenticatedEncryptionException;

    /**
     * @param keyStrengthType
     * @return
     */
    int byteSizeLength(@KeyStrength int keyStrengthType);
}
