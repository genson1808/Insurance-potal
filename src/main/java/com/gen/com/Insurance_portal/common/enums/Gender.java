package com.gen.com.Insurance_portal.common.enums;


public enum Gender {
    MALE("nam"), // 0
    FEMALE("nữ"), // 1
    NONE("chưa có thông tin"); // 2

    private String value;

    Gender(String value) {
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
