package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import com.spacetech.moovme.Exeptions.CantApplyDiscountExeption;
import com.spacetech.moovme.Users.User;


public class AssetParking {

    private final Zone zone;

    AssetParking(Zone zone){
        this.zone=zone;
    }

    public Asset rentAsset(AssetType assetType) throws AssetTypeDoesNotExistInSpecifiedZone {
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

    public Fee applyDiscount(Travel actualTravel, User user, Fee fee) throws CantApplyDiscountExeption {
        return zone.applyDiscount(actualTravel,user,fee);
    }
}
