package com.gen.com.Insurance_portal.common.enums;

public enum SubscriptionType {
    POSTPAID("trả sau"),
    PREPAID("trả trước");

    private String value;

    SubscriptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
