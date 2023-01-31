package com.gen.com.Insurance_portal.common.enums;

public enum AutoExtendMethod {

    Full("Thu đủ"),
    TakeAllType1("Tận thu loại 1"),
    TakeAllType2("Tận thu loại 2");

    private String value;

    AutoExtendMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
