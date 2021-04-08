package com.kintexgroup.hkpsi.common.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 *
 * @author gradylo
 * @since 2020/9/18 9:12 下午
 */
@Slf4j
public class Md5Util {

    static final int SALT_BYTES = 3;

    /**
     * md5和sha-1混合加密
     *
     * @param inputText 要加密的内容
     * @return String md5和sha-1混合加密之后的密码
     */
    public static String md5AndSha(String inputText) {
        return sha(md5(inputText));
    }


    /**
     * md5加密
     *
     * @param inputText 要加密的内容
     * @return String  md5加密之后的密码
     */
    public static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }


    /**
     * sha-1加密
     *
     * @param inputText 要加密的内容
     * @return sha-1加密之后的密码
     */
    public static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }


    /**
     * md5或者sha-1加密
     *
     * @param inputText     要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return String  md5或者sha-1加密之后的结果
     */
    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes(StandardCharsets.UTF_8));
            byte[] s = m.digest();
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * byte[]字节数组 转换成 十六进制字符串
     *
     * @param arr 要转换的byte[]字节数组
     * @return String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }


    /**
     * 生成含有随机盐的密码
     *
     * @param password 要加密的密码
     * @return String 含有随机盐的密码
     */
    public static String getSaltMd5AndSha(String password) {
        password = md5(password);
        // 固定生成的KEY
        String salt = "2467291379842513";
        password = md5AndSha(password + salt);
        int size = 48;
        char[] cs = new char[size];
        for (int i = 0; i < size; i += SALT_BYTES) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }


    /**
     * 验证加盐后是否和原密码一致
     *
     * @param password 原密码
     * @param md5str   加密之后的密码
     * @return boolean true表示和原密码一致   false表示和原密码不一致
     */
    public static boolean getSaltVerifyMd5AndSha(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        int size = 48;
        for (int i = 0; i < size; i += SALT_BYTES) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String salt = new String(cs2);
        String encrypPassword = md5AndSha(password + salt);

        // 加密密码去掉最后8位数
        encrypPassword = encrypPassword.substring(0, encrypPassword.length() - 8);

        return encrypPassword.equals(String.valueOf(cs1));
    }

    public static void main(String[] args) {
        // 原密码
        String plaintext = "123456";
        String md5 = Md5Util.md5(plaintext);
        log.info(md5);
        // 获取加盐后的MD5值
        String ciphertext = Md5Util.getSaltMd5AndSha(md5);
        log.info("加盐后MD5：" + ciphertext);
        log.info("是否是同一字符串:" + Md5Util.getSaltVerifyMd5AndSha(md5, ciphertext));
    }

}
