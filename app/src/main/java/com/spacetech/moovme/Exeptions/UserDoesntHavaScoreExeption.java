package com.spacetech.moovme.Exeptions;

public class UserDoesntHavaScoreExeption extends Throwable {
    public UserDoesntHavaScoreExeption(){
        super("El usuario no gano ningun punto previamente");
    }
}
