package com.enashtech.rookieserver.handleException;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(int id){
        super("Could not found customer: " + id);
    }
}
