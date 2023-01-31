package com.gen.com.Insurance_portal.common.enums;

public enum ProductStatus {
    PENDING("Chờ duyệt"),       // 0
    APPROVED("Đã duyệt"),       // 1
    REJECT("Từ chối duyệt"),    // 2
    DRAFT("Lưu nháp");          // 3

    private String value;

    ProductStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
