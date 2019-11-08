package com.spacetech.moovme.Exceptions;

public class AssetTypeDoesNotExistInSpecifiedZoneException extends Exception {
    public AssetTypeDoesNotExistInSpecifiedZoneException(){
        super("El tipo de asset selecionado no existe en la zona");
    }
}
