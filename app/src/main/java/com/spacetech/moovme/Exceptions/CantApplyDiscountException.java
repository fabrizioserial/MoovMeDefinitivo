package com.spacetech.moovme.Exceptions;

public class CantApplyDiscountException extends Exception {
    public CantApplyDiscountException(){
        super("El descuento no es aplicable");
    }
}
