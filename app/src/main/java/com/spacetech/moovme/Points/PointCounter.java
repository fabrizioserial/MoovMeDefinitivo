package com.spacetech.moovme.Points;

import com.spacetech.moovme.Assets.Travel;

public class PointCounter {


    public Points calculateAquiredPoints(Travel travel){
        if(travel.checkTime(System.nanoTime())){
            return new Points((int) (travel.getAsset().getAssetType().getPoint()*1.2));
        }
        else return new Points(travel.getAsset().getAssetType().getPoint());
    }
}
