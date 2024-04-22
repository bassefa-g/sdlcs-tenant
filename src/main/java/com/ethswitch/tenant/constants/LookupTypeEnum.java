package com.ethswitch.tenant.constants;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum LookupTypeEnum {

    ORGANIZATION_TYPE("organizationtype", "Organization Type"),
    LANGUAGES("languages", "Languages");

    private static final Map<String, LookupTypeEnum> map = new HashMap<>();

    static {
        for (LookupTypeEnum type : LookupTypeEnum.values()) {
            map.put(type.value, type);
        }
    }

    private final String value;
    private final String text;

    LookupTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public static LookupTypeEnum fromString(String value) {
        return map.get(value);
    }
}
