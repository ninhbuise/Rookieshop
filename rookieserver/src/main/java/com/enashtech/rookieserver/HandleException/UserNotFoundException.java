package com.enashtech.rookieserver.HandleException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id){
        super("Could not find user " + id);
    }
}
