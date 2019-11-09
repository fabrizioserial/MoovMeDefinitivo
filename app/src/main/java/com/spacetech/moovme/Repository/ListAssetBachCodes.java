package com.spacetech.moovme.Repository;

public class ListAssetBachCodes {
    Integer lastCodeValue=0;
    public ListAssetBachCodes(){

    }
    public Integer createNewCode(){
        lastCodeValue = lastCodeValue +1;
        return lastCodeValue;
    }
}
