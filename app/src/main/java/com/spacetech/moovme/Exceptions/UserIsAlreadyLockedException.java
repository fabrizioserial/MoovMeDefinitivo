package com.spacetech.moovme.Exceptions;

public class UserIsAlreadyLockedException extends Exception{

    public UserIsAlreadyLockedException(){

        super("User was already in state");

    }

}
