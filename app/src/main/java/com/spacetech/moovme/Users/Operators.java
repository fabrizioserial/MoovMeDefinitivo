package com.spacetech.moovme.Users;

import java.io.Serializable;

public abstract class Operators implements Serializable {

    public String getName(Data data){
        return data.getName();
    }
}
