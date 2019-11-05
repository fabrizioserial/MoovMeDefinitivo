package com.spacetech.moovme.Exeptions;

public class UserIsAlreadyLockedExeption extends Exception{

    UserIsAlreadyLockedExeption(){

        super("User was already in state");

    }

}
