package com.gen.com.Insurance_portal.common.enums;

public enum PromoCodeStatus {

    NotUsed("Chưa sử dụng"),
    Used("Đã sử dụng"),
    Pending("Đang đợi thanh toán");

    private String value;

    PromoCodeStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
