package com.gen.com.Insurance_portal.common.enums;

public enum PurchaseMethod {
    Paypal("paypal"),
    SMS("SMS");

    private String value;

    PurchaseMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
