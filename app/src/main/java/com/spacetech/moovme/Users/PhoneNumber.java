package com.spacetech.moovme.Users;

public class PhoneNumber {

    private final int number;

    public PhoneNumber(int number){
        this.number=number;
    }


    public boolean equals(PhoneNumber o){
        return this.getNumber() == o.getNumber();
    }

    public int getNumber() {
        return number;
    }
}
