package com.spacetech.moovme.Exceptions;

public class PriceIsAlreadySetExeption extends Exception {
    public PriceIsAlreadySetExeption(){
        super("el precio y esta seteado");
    }
}
