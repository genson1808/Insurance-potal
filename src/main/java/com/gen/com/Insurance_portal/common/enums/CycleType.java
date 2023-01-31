package com.gen.com.Insurance_portal.common.enums;

public enum  CycleType {
    D("Ngày"),
    W("Tuần"),
    M("Tháng"),
    Y("Năm");

    private String value;

    CycleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
