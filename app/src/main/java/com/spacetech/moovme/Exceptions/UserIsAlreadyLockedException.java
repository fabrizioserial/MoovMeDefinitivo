package com.spacetech.moovme.Exceptions;

public class UserIsAlreadyLockedException extends Exception{

    UserIsAlreadyLockedException(){

        super("User was already in state");

    }

}
