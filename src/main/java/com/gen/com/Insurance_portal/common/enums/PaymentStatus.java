package com.gen.com.Insurance_portal.common.enums;

public enum PaymentStatus {
    Paid("Đã thanh toán"),
    Unpaid("Chưa thanh toán");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
