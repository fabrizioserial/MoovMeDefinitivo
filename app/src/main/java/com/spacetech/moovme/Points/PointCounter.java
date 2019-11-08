package com.spacetech.moovme.Points;

import com.spacetech.moovme.Assets.Travel;

import java.util.Calendar;

public class PointCounter {

    //clase que cuenta cuanto puntos ganaste por viaje
    public Points calculateAquiredPoints(Travel travel){
        if(travel.checkTime()){ //se fija el tiempo en el viaje
            return new Points((int) (travel.getAsset().getAssetType().getPoint()*1.2*travel.getTime()));//se le da un 20% mas de puntos si cumplio con el tiempo
        }
        else return new Points(travel.getAsset().getAssetType().getPoint()*travel.getTime());
    }

    public Points calculateAquiredPointsTimeTest(Travel travel,int time){//mismo que el anterior pero con tiempo ajustable para testear
        if(travel.checkTimeTest(time)){
            return new Points ((int) (travel.getAsset().getAssetType().getPoint()*1.2*time));
        }
        else return new Points(travel.getAsset().getAssetType().getPoint());
    }
}
