package com.lucky.dto;

/**
 * Created by lucky on 1/9/17.
 */
public class ErrorReporter {
    private int code;
    private String message;

    public ErrorReporter() {
    }

    public ErrorReporter(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
