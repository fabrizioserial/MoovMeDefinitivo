package com.spacetech.moovme.Assets;


import com.spacetech.moovme.Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import com.spacetech.moovme.Users.Data;


public class AssetParking {

    private final Zone zone;

    AssetParking(Zone zone){
        this.zone=zone;
    }

    public Asset rentAsset(AssetType assetType)throws AssetTypeDoesNotExistInSpecifiedZone {
        Asset assetToOccupy=zone.getAssetwithDesignatedType(assetType);
        if(assetToOccupy != null){
            return assetToOccupy;
        }
        throw new AssetTypeDoesNotExistInSpecifiedZone();
    }

    public double returnAsset(Travel travel, int points) {
        return zone.calculateFee(travel.getAsset(),points);
    }

    public Integer ganarPuntos(Travel actualTravel, Data data, Integer points) {
        return zone.actualizarPuntos(actualTravel,data,points);
    }

    public Zone getZone() {
        return zone;
    }
}
