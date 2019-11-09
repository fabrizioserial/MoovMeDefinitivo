package com.spacetech.moovme.Users;

public class PhoneNumber {

    private final int number;

    public PhoneNumber(int number){
        this.number=number;
    }


    public boolean equals(Object o){
        if(o instanceof PhoneNumber){
            return ((PhoneNumber) o).getNumber()== this.getNumber();
        }
        else return false;
    }

    public int getNumber() {
        return number;
    }
}
