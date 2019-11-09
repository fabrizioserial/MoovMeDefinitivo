package com.spacetech.moovme.Users;


import android.icu.util.Calendar;

public class Timer {//el timer solo compara el tiempo desde que se creo y devuelve el tiempo total que existio

    private int startTime;

    public Timer(){
        this.startTime=Calendar.MINUTE;
    }

    public int actualTimePassSinceStartedTimer(int actualTime){
        return actualTime-startTime;
    }
    public int compareTime(int actualTime) {
        return actualTime-startTime;
    }


}
