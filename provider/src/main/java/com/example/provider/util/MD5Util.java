package com.example.provider.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.util
 * @className: MD5Util
 * @author: LiYinjian
 * @date: 2022/7/21 9:15
 * @version: 1.0
 */

public class MD5Util {
    private final static String salt = "1a2b3c";

    public static String MD5Encrypt(String pw) {
        String s = salt.substring(0, 2) + pw + salt.substring(4, 6);
        return DigestUtils.md5Hex(s);
    }

    public static void main(String[] args) {
        String pw = "123456789";
        System.out.println(MD5Util.MD5Encrypt(pw));
    }
}
