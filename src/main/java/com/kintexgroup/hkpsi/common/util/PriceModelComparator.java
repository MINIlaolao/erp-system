package com.kintexgroup.hkpsi.common.util;

import com.kintexgroup.hkpsi.information.model.RecordAboutQueryTheBidPrice;

import java.util.Comparator;

/**
 * @author LMAO
 * @since 2020/12/3 10:07
 */
public class PriceModelComparator implements Comparator<RecordAboutQueryTheBidPrice> {

    /**
     * 根据PriceModel的createAt比较顺序
     *
     * @param o1 O1
     * @param o2 O2
     * @return o1time<o2time = - 1
        * * o1time>o2time = 1
     */
    @Override
    public int compare(RecordAboutQueryTheBidPrice o1, RecordAboutQueryTheBidPrice o2) {
        var o1Date = o1.getBidDate();
        var o2Date = o2.getBidDate();

        return o1Date.compareTo(o2Date);
    }
}


