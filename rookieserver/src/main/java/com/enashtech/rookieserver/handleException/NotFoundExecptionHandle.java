package com.enashtech.rookieserver.handleException;

public class NotFoundExecptionHandle extends RuntimeException{
    public NotFoundExecptionHandle(String msg) {
        super(msg);
    }
}
