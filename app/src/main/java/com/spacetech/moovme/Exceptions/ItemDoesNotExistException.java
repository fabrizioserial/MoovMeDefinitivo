package com.spacetech.moovme.Exceptions;

public class ItemDoesNotExistException extends Exception {

    public ItemDoesNotExistException() {
        super("El item que quiere eliminar no se encuentra en el repositorio");
    }
}

