package com.spacetech.moovme.Exceptions;

public class UserIsAlreadyLockedExeption extends Exception{

    UserIsAlreadyLockedExeption(){

        super("User was already in state");

    }

}
