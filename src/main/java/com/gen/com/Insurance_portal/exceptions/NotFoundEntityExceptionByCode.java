package com.gen.com.Insurance_portal.exceptions;

public class NotFoundEntityExceptionByCode extends RuntimeException {
    private Object code;
    private String entityName;

    public NotFoundEntityExceptionByCode(Object code, String entityName){
        this.code = code;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        String mess = "not found entity " + entityName + " with code: " + code + "!!!";
        return mess;
    }
}
