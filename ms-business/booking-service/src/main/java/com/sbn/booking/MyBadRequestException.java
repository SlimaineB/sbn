package com.sbn.booking;

public class MyBadRequestException extends Exception {
    public MyBadRequestException(String body) {
        super(body);
    }
}