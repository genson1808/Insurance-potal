package com.gen.com.Insurance_portal.common.enums;

public enum OrderStatus {
    Complete("Đã hoàn thành"),
    Incomplete("Chưa hoàn thành"),
    Expired("Hết hạn"),
    Paid("Đã thanh toán"); // Đã thanh toán nhưng tạo hợp đồng thất bại

   private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
