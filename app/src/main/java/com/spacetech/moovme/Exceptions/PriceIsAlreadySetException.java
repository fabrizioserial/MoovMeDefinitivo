package com.spacetech.moovme.Exceptions;

public class PriceIsAlreadySetException extends Exception {
    public PriceIsAlreadySetException(){
        super("el precio y esta seteado");
    }
}
