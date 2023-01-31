package com.gen.com.Insurance_portal.common.enums;

public enum SysAdminType {
    ADMIN("Admin"),
    ProductProvider("Partner");

    private String value;

    SysAdminType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
