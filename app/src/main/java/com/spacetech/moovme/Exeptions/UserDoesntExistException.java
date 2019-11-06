package com.spacetech.moovme.Exeptions;

public class UserDoesntExistException extends Exception{

    public UserDoesntExistException(){
        super("User doesnt exist");

    }

}
