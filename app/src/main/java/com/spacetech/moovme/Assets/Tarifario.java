package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exceptions.PriceIsAlreadySetException;

import java.util.ArrayList;
import java.util.HashMap;

public class Tarifario {

    private HashMap<AssetType, Fee> pricePerAsset=new HashMap<>();
    private ArrayList<AssetTypePerFee> assetTypePerFees;

    public Tarifario() {
        assetTypePerFees = new ArrayList<>();
    }

    public void addAssetPricePerZone(AssetType assetType, Fee fee) throws PriceIsAlreadySetException {
        /*if(!pricePerAsset.containsKey(assetType)){
            pricePerAsset.put(assetType, fee);
        }
        else throw new PriceIsAlreadySetException();

         */
        boolean assetypeexist = false;
        for(AssetTypePerFee assetTypePerFee:assetTypePerFees){
            if(assetTypePerFee.getAssetType().equals(assetType)){
                assetypeexist = true;
            }
        }

        if(assetypeexist){
            throw new PriceIsAlreadySetException();
        }else {
            assetTypePerFees.add(new AssetTypePerFee(assetType,fee));
        }
    }

    public Fee calculatePrice(AssetType assetTypeUsed) {
        for(AssetTypePerFee assetType: assetTypePerFees){
            if(assetType.getAssetType().equals(assetTypeUsed)){
                return assetType.getFee();
            }
        }

        return null;
        //return pricePerAsset.get(assetTypeUsed);
    }
}