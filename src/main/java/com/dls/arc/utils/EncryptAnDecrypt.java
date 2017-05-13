package com.dls.arc.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class EncryptAnDecrypt {
    private static final String ENCRYPT_TYPE = "DES";
    private static final String ENCRYPT_KEY = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()";

    public static String encrypt(String data) {
        byte[] bt = encrypt(data.getBytes(), ENCRYPT_KEY.getBytes());
//        return new BASE64Encoder().encode(bt);
        return Base64.encodeBase64String(bt);
    }

    public static String decrypt(String data) {
        if (data == null)
            return null;
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] buf = decoder.decodeBuffer(data);

        byte[] buf = Base64.decodeBase64(data);
        byte[] bt = new byte[0];
        try {
            bt = decrypt(buf, ENCRYPT_KEY.getBytes());
            return new String(bt);
        } catch (Exception e) {
            return data;
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key) {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        try {
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);

            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPT_TYPE);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);

            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

            return cipher.doFinal(data);
        } catch (Exception e) {
            return data;
        }
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPT_TYPE);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
