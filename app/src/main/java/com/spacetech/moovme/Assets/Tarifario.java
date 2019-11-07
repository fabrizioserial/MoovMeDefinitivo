package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exeptions.PriceIsAlreadySetExeption;

import java.util.HashMap;

public class Tarifario {

    HashMap<AssetType,Price> pricePerAsset;

    public Tarifario() {
        pricePerAsset = new HashMap<>();
    }

    public void addAssetPricePerZone(AssetType assetType, Price price) throws PriceIsAlreadySetExeption {
        if(!pricePerAsset.containsKey(assetType)){
            pricePerAsset.put(assetType,price);
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
}