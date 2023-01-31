package com.gen.com.Insurance_portal.common.enums;

public enum TransactionStatus {
    Success("Thành công"),
    Error("Thất bại");

    private String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
