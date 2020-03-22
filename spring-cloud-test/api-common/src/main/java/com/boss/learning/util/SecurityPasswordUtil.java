package com.boss.learning.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密密码工具类
 * @author Zzwen
 * @date 2020/3/20 9:36
 */
public class SecurityPasswordUtil {
    /**
     * 生成md5加密码
     * @param password
     * @return
     */

    public static String getDigestPassword(String password){
        String encode = "";
        try{
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(password.getBytes());
            byte[] digestPassword = md5.digest();
            BASE64Encoder base64Encoder = new BASE64Encoder();
            encode = base64Encoder.encode(digestPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }
}
