package com.zxp.helloplus.model.enumparam;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 支付网关类型 (success:交易成功,failure:交易失败)
*/
public enum PayGatewayStatusEnum {
    SUCCESS("success", "交易成功"),FAILURE("failure", "交易失败");

    private PayGatewayStatusEnum(String code,String description) {
        this.code = code;
        this.description = description;
    }

    private String description;
    private String code;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获得所有Map List 值；
     * 
     * @return
     */
    public static Map<String, String> getMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (PayGatewayStatusEnum penum : PayGatewayStatusEnum.values()) {
            map.put(penum.getCode(), penum.getDescription());
        }
        return map;
    }

    /**
     * 根据code取名称
     * 
     * @param code
     * @return
     */
    public static String formCode(String code) {
        for (PayGatewayStatusEnum u : PayGatewayStatusEnum.values()) {
            if (u.getCode().equals(code))
                return u.getDescription();
        }
        return null;
    }


}