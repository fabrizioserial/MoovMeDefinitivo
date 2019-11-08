package com.spacetech.moovme.Exceptions;

public class ZoneAlreadyExistsException extends Exception {
    public ZoneAlreadyExistsException(){
        super("Zone already exists");
    }
}
