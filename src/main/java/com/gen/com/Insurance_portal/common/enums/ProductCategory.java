package com.gen.com.Insurance_portal.common.enums;

public enum ProductCategory {
    HEALTH("health"),
    ACCIDENT("acctident"),
    TRAVEL("travel"),
    PROPERTY("property"),
    CarMotor("CarMoto");

    private String value;

    ProductCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
