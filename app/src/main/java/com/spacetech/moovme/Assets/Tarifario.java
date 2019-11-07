package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exeptions.PriceIsAlreadySetExeption;

import java.util.HashMap;

public class Tarifario {

    HashMap<AssetType, Fee> pricePerAsset=new HashMap<>();

    public Tarifario() {
    }

    public void addAssetPricePerZone(AssetType assetType, Fee fee) throws PriceIsAlreadySetExeption {
        if(!pricePerAsset.containsKey(assetType)){
            pricePerAsset.put(assetType, fee);
        }
        else throw new PriceIsAlreadySetExeption();
    }


    public double calculatePrice(Asset assetUsed, Discount discount, int points) {
        //try {
            return 3;//discount.applyDiscount(assetUsed.getAssetType(), points, assetUsed.getPrice());
        /*} catch (CantApplyDiscountExeption cantApplyDiscountExeption) {
            //Avisar que no se pudo aplicar descuento
            //return assetUsed.getPrice();
            //TODO fix get price, get price from price table
            return 4;
        }*/
    }
    public Fee calculatePrice(AssetType assetTypeUsed){
        return pricePerAsset.get(assetTypeUsed);
    }
}