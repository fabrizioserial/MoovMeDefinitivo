package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exceptions.PriceIsAlreadySetException;

import java.util.HashMap;

public class Tarifario {

    HashMap<AssetType, Fee> pricePerAsset=new HashMap<>();

    public Tarifario() {
    }

    public void addAssetPricePerZone(AssetType assetType, Fee fee) throws PriceIsAlreadySetException {
        if(!pricePerAsset.containsKey(assetType)){
            pricePerAsset.put(assetType, fee);
        }
        else throw new PriceIsAlreadySetException();
    }

    public Fee calculatePrice(AssetType assetTypeUsed){
        return pricePerAsset.get(assetTypeUsed);
    }
}