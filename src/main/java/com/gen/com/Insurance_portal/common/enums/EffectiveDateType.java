package com.gen.com.Insurance_portal.common.enums;

public enum EffectiveDateType {

    NONE("Tự chọn"),
    T("Ngày T"),
    T1("Ngày T+1"),
    T1ToTx("Ngày T+X");

    private String value;

    EffectiveDateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
