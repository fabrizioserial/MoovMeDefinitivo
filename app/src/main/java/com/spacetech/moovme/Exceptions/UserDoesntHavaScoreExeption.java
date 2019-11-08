package com.spacetech.moovme.Exceptions;

public class UserDoesntHavaScoreExeption extends Throwable {
    public UserDoesntHavaScoreExeption(){
        super("El usuario no gano ningun punto previamente");
    }
}
