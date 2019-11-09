package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Users.Timer;

import java.util.Calendar;

public class Travel {

    private final Asset asset;
    private final Timer timer;
    private final int expectedTime;

    public Travel(Asset asset, int expectedTimeInMinutes){
        this.asset=asset;
        this.timer= new Timer();
        this.expectedTime=expectedTimeInMinutes;
    }

    public Asset getAsset(){
        return asset;
    }

    public boolean checkTime(){
        if(timer.actualTimePassSinceStartedTimer(Calendar.MINUTE)<=expectedTime) return true;
        else return false;
    }

    public boolean checkTimeTest(int time) {
        if(timer.actualTimePassSinceStartedTimer(time)<=expectedTime) return true;
        else return false;
    }

    public int getTime(){
        return timer.actualTimePassSinceStartedTimer(Calendar.MINUTE);
    }
}
