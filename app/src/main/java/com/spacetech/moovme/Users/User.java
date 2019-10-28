package com.spacetech.moovme.Users;

public class User {


    private final PhoneNumber phoneNumber;
    private final String name;
    private boolean isLocked=false;

    public User(PhoneNumber aPhoneNumber,String name){
        this.phoneNumber=aPhoneNumber;
        this.name=name;
    }

    public void userLocking(boolean lockUser) {
            isLocked=lockUser;
    }

    public PhoneNumber getPhoneNumber(){
        return phoneNumber;
    }

    public boolean getLock(){ //for testing
        return isLocked;
    }

}

