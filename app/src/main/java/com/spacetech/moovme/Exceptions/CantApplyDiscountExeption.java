package com.spacetech.moovme.Exceptions;

public class CantApplyDiscountExeption extends Exception {
    public CantApplyDiscountExeption(){
        super("El descuento no es aplicable");
    }
}
