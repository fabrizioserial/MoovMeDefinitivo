package com.spacetech.moovme.Repository;

public class ListAssetBachCodes {
    Integer lastCodeValue=0;
    public Integer createNewCode(){
        lastCodeValue++;
        return lastCodeValue;
    }
}
