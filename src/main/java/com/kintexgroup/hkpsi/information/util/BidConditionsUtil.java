package com.kintexgroup.hkpsi.information.util;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author junhangs
 * @author 2021-03-18 09:21
 **/
public class BidConditionsUtil {
    static private HashMap<String, String> map = new HashMap<>() {{
        put("sku", "`source_sku`");
        put("brand", "`source_oem`");
        put("description", "`source_description`");
        put("modelNumber", "`source_model_number`");
        put("modelName", "`source_model_name`");
        put("model", "`source_model`");
        put("capacity", "`source_capacity`");
        put("carrier", "`source_carrier`");
        put("color", "`source_color`");
        put("condition", "`source_condition`");
        put("grade", "`source_grade`");
        put("fmipLocked", "`source_FMiP_locked`");
        put("warehouse", "`source_warehouse`");
        put("simLocked", "`source_SIM_locked`");
        put("lcdHealth", "`source_LCD_health`");
    }};
    
    static public String getFieldByName(String name) {
        return map.get(name);
    }
    
    static public List<String> getFieldByNames(List<String> names) {
        return names.stream().map(name -> map.get(name)).collect(Collectors.toList());
    }
}
