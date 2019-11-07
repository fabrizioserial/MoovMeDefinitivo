package com.spacetech.moovme.Exeptions;

public class PriceIsAlreadySetExeption extends Exception {
    public PriceIsAlreadySetExeption(){
        super("el precio y esta seteado");
    }
}
