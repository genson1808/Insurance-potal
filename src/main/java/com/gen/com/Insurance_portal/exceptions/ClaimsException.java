package com.gen.com.Insurance_portal.exceptions;

import java.util.Map;

public class ClaimsException extends RuntimeException {
    private Map<String, String> messages;

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

    public ClaimsException(Map<String, String> messages) {
        this.messages = messages;
    }
}
