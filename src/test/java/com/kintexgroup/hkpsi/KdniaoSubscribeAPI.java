package com.kintexgroup.hkpsi;

import com.kintexgroup.hkpsi.common.util.KdnUtil;

/**
 * @author LMAO
 * @since 2020/12/7 14:40
 */
public class KdniaoSubscribeAPI {

    public static void main(String[] args) throws Exception {
        var result = KdnUtil.kdnApiLogistics("FEDEX_GJ","930842303183","8001");
        System.out.println(result.toString());
    }
    
}



