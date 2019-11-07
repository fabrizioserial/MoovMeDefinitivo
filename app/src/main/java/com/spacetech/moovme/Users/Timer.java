package com.spacetech.moovme.Users;


public class Timer {

    private long startTime;

    public Timer(long startTime){
        this.startTime=startTime;
    }
    public long actualTimePassSinceStartedTimer(final long actualTime){
        return actualTime-startTime;
    }
    public long compareTime(long actualTime) {
         return actualTime-startTime;
    }


}
