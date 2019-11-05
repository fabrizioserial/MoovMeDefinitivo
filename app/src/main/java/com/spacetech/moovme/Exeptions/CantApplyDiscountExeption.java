package com.spacetech.moovme.Exeptions;

public class CantApplyDiscountExeption extends Exception {
    public CantApplyDiscountExeption(){
        super("El descuento no es aplicable");
    }
}
