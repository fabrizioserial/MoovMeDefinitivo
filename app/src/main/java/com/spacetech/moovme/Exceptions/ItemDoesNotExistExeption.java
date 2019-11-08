package com.spacetech.moovme.Exceptions;

public class ItemDoesNotExistExeption extends Exception {

    public ItemDoesNotExistExeption() {
        super("El item que quiere eliminar no se encuentra en el repositorio");
    }
}

