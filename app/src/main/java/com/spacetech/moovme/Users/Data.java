package com.spacetech.moovme.Users;

public class Data {
    private final String name;
    private final Users.PhoneNumber phoneNumber;

    Data(String name){
        this.name=name;
        this.phoneNumber=null;
    }

    public Data(String name, Users.PhoneNumber phoneNumber){
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public Users.PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }
}
