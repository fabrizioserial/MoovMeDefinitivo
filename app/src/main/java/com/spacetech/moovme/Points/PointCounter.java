package com.spacetech.moovme.Points;

import com.spacetech.moovme.Assets.Travel;

import java.util.Calendar;

public class PointCounter {


    public Points calculateAquiredPoints(Travel travel){
        if(travel.checkTime()){
            return new Points((int) (travel.getAsset().getAssetType().getPoint()*1.2*travel.getTime()));
        }
        else return new Points(travel.getAsset().getAssetType().getPoint());
    }

    public Points calculateAquiredPointsTimeTest(Travel travel,int time){
        if(travel.checkTimeTest(time)){
            return new Points ((int) (travel.getAsset().getAssetType().getPoint()*1.2*time));
        }
        else return new Points(travel.getAsset().getAssetType().getPoint());
    }
}
