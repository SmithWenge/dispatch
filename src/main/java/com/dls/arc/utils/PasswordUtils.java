package com.dls.arc.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    private static final String ENCRYPT_TYPE = "SHA-256";
    private static final String ENCRYPT_CHARSET = "UTF-8";

    public static String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPT_TYPE);
            md.update(password.getBytes(ENCRYPT_CHARSET));
            byte[] photoByte = md.digest();

            //convert the byte to hex format method 2
            StringBuilder hexString = new StringBuilder();
            for (int i=0; i<photoByte.length; i++) {
                String hex = Integer.toHexString(0xff & photoByte[i]);
                if(hex.length() ==1 ) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return password;
        } catch (UnsupportedEncodingException e) {
            return password;
        }
    }
}
