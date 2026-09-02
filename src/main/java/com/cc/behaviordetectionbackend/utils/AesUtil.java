package com.cc.behaviordetectionbackend.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName AesUtil
 * @Description Aes 工具类
 * @Author cc
 * @Date 2025/11/29 11:18
 * @Version 1.0.0
 */
public class AesUtil {
    /**
     * 此处使用AES-128-ECB加密模式，key需要为16位。
     */
    private static final String KEY_ALGORITHM = "1234567890123456";
    /**
     * 加密
     * @param sSrc 需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String Encrypt(String sSrc) throws Exception {
        byte[] raw = KEY_ALGORITHM.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 解密
     * @param sSrc 需要解密的字符串
     * @return
     * @throws Exception
     */
    public static String Decrypt(String sSrc) throws Exception {
        try {
            byte[] raw = KEY_ALGORITHM.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, StandardCharsets.UTF_8);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
