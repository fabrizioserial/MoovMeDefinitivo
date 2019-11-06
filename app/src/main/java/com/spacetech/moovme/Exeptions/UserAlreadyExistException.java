package com.spacetech.moovme.Exeptions;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(){
        super("User is already registered");
    }
}
