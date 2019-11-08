package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exceptions.AssetTypeDoesNotExistInSpecifiedZoneException;
import com.spacetech.moovme.Exceptions.CantApplyDiscountException;
import com.spacetech.moovme.Users.User;


public class AssetParking {

    private final Zone zone;
    private  String name;

    AssetParking(Zone zone, String name){
        this.zone=zone;
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public Asset rentAsset(AssetType assetType) throws AssetTypeDoesNotExistInSpecifiedZoneException {
        return zone.getAssetwithDesignatedType(assetType);
    }

    public Fee returnAsset(Travel travel, User user) {
        return zone.returnAsset(travel,user);
    }

    public Zone getZone() {
        return zone;
    }

    public boolean canApplyDiscount(Travel actualTravel, User user) {
        return zone.canApplyDiscount(actualTravel,user);
    }

    public Fee applyDiscount(Travel actualTravel, User user, Fee fee) throws CantApplyDiscountException {
        return zone.applyDiscount(actualTravel,user,fee);
    }
}
