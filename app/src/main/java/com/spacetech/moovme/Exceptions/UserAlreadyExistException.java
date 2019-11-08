package com.spacetech.moovme.Exceptions;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(){
        super("User is already registered");
    }
}
