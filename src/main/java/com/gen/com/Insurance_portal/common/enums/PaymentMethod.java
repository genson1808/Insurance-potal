package com.gen.com.Insurance_portal.common.enums;

public enum PaymentMethod {
    Paypal("paypal"),
    ViettelPay("ViettelPay"),
    ViettelPayPro("ViettelPay Pro"),
    ViettelTelecom("ViettelTelecom");

    private String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
