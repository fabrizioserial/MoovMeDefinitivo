package com.spacetech.moovme.Users;

public class Data {
    private final String name;
    private final PhoneNumber phoneNumber;
    //guarda datos principales de user y admin

    public Data(String name){ //constructor para admins que solo tienen nombre
        this.name=name;
        this.phoneNumber=null;
    }

    public Data(String name, PhoneNumber phoneNumber){ //constructor para usuarios
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

}
