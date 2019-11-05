package com.spacetech.moovme.Exeptions;

public class UserIsNotRegistered extends Exception {

    public UserIsNotRegistered(){
        super("User is not registered");
    }
}
