package com.kintexgroup.hkpsi.common.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 运营商
 *
 * @author LMAO
 * @since 2020/11/27 16:13
 */
public enum Carrier {
    /**
     *
     */
    VERSION("Verizon"),
    SPRINT("Sprint"),
    AT_AND_T("AT&T"),
    T_MOBILE("T-Mobile"),
    GENERIC("Generic"),
    UNKNOWN("Unknown"),
    LOCKED("Locked");
    private final String carrierName;

    Carrier(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public static List<String> listCarriers() {
        var vendors = new ArrayList<String>(Carrier.values().length);
        for (Carrier value : Carrier.values()) {
            vendors.add(value.getCarrierName());
        }
        return vendors;
    }
}
