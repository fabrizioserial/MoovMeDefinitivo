package com.spacetech.moovme.Users;

public class Data {
    private final String name;
    private final PhoneNumber phoneNumber;

    Data(String name){
        this.name=name;
        this.phoneNumber=null;
    }

    Data(String name,PhoneNumber phoneNumber){
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
