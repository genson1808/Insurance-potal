package com.gen.com.Insurance_portal.common.enums;

public enum AttributeFormat {

    DropdownList("DropdownList"),
    DatePicker("DatePicker"),
    Text("Text"),
    TextArea("TextArea"),
    NumberInput("Number Input"),
    FileUploading("File Uploading"),
    Checkbox("Checkbox"),
    Bloon("Bloon"),
    Table("Table"),
    Email("Email"),
    Description("Description");

    private String value;

    AttributeFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
