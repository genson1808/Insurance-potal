package com.gen.com.Insurance_portal.common.enums;

public enum RegisterStatus {
    Succeeded("succeeded!"),
    ExistUser("exist user."),
    NotVerify("not verify."),
    None("none");

    private String value;

    RegisterStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
