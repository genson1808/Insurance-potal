package com.gen.com.Insurance_portal.common.enums;

public enum PolicyGroup {
    PolicyGroup1("Nhóm hợp đồng 1"),
    PolicyGroup2("Nhóm hợp đồng 2"),
    None("Không thuộc nhóm nào");

    private String value;

    PolicyGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
