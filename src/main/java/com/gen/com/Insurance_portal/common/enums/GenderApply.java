package com.gen.com.Insurance_portal.common.enums;

public enum GenderApply {
    ALL("tất cả"),
    MALE("nam"),
    FEMALE("nữ");

    private String value;

    GenderApply(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
