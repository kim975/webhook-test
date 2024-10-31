package com.zerobase.user.util;

import com.zerobase.user.exception.BaseException;
import com.zerobase.user.exception.BasicErrorCode;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {

    private final static String ALG = "AES/CBC/PKCS5Padding";
    private final static String KEY = "01234567890123456789012345678901";
    private final static String IV = KEY.substring(0, 16); // 16byte

    public static String aes256Encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(ALG);

            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new BaseException(BasicErrorCode.ENCRYPT_ERROR);
        }
    }

    public static String aes256Decrypt(String text) {
        try {

            Cipher cipher = Cipher.getInstance(ALG);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(text);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");

        } catch (Exception e) {
            throw new BaseException(BasicErrorCode.ENCRYPT_ERROR);
        }
    }

    public static String sha256Encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes("UTF-8"));
            return bytesToHex(md.digest());
        } catch (Exception e) {
            throw new BaseException(BasicErrorCode.ENCRYPT_ERROR);
        }
    }

    private static String bytesToHex(byte[] bytes) {

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
