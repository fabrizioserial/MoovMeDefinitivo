package com.spacetech.moovme.Assets;

import Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import Users.Data;

public class AssetParking {

    private final Assets.Zone zone;

    AssetParking(Assets.Zone zone){
        this.zone=zone;
    }

    public Asset rentAsset(AssetType assetType) {
        Asset assetToOccupy=null;
        try{
            assetToOccupy=zone.getAssetwithDesignatedType(assetType);
        } catch (AssetTypeDoesNotExistInSpecifiedZone assetTypeDoesNotExistInSpecifiedZone) {
            assetTypeDoesNotExistInSpecifiedZone.printStackTrace();
            //TODO throw toast to inform that asset does not exist
        }
        return assetToOccupy;
    }

    public double returnAsset(Assets.Travel travel, int points) {
        return zone.calculateFee(travel.getAsset(),points);
    }

    public Integer ganarPuntos(Assets.Travel actualTravel, Data data, Integer points) {
        return zone.actualizarPuntos(actualTravel,data,points);
    }

    public Assets.Zone getZone() {
        return zone;
    }
}
