package com.soliduslabs.hash2link.domain.dto;

public class ReqResMessage {
    private String message;

    public ReqResMessage() {

    }

    public ReqResMessage(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
