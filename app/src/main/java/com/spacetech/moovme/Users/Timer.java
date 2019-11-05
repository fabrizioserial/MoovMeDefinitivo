package com.spacetech.moovme.Users;


public class Timer {

    private long startTime;

    public Timer(long startTime){
        this.startTime=startTime;
    }
    public long actualTimePassSinceStartedTimer(final long actualTime){
        return actualTime-startTime;
    }
    public int compareTime(long expectedTime) {
         return 0;
    }

    //TODO implement methods


}
