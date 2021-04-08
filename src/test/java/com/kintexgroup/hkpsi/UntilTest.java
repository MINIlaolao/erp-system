package com.kintexgroup.hkpsi;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.jupiter.api.Test;

/**
 * @author LMAO
 * @since 2020/12/5 14:46
 */
public class UntilTest {

    @Test
    public void testUntil() {
        String content = "60.0";
        
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, new byte[]{-95, -40, -102, 10, 96, -119, -66, 31, 107, -118, 96, -76, 104, 35, -31, 35});

        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex);

        System.out.println(content.equals(decryptStr));
        System.out.println(encryptHex);
    }
}


