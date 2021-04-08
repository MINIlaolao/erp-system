package com.kintexgroup.hkpsi.common.util;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.NonNull;

/**
 * @author LMAO
 * @since 2020/12/5 15:19
 */
public final class AsymmetricCryptoUtil {

    private AsymmetricCryptoUtil() {
    }


    private final byte[] key = {-95, -40, -102, 10, 96, -119, -66, 31, 107, -118, 96, -76, 104, 35, -31, 35};


    private final SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

    public String encrypt(@NonNull String raw) {
        return aes.encryptHex(raw);
    }

    public String decrypt(@NonNull String password) {
        return aes.decryptStr(password);
    }
}


