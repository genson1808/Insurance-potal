package com.gen.com.Insurance_portal.exceptions;

public class MessageException extends RuntimeException {
    private String mess;

    public MessageException(String message) {
        super(message);
        this.mess = message;
    }

    @Override
    public String getMessage() {
        return mess;
    }
}

