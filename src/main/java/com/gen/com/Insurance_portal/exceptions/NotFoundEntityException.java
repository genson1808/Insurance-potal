package com.gen.com.Insurance_portal.exceptions;

public class NotFoundEntityException extends RuntimeException {
    private Object id;
    private String entityName;

    public NotFoundEntityException(Object id, String entityName){
        this.id = id;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        String mess = "not found entity " + entityName + " with id: " + id + "!!!";
        return mess;
    }
}