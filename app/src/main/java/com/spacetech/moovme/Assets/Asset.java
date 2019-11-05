package com.spacetech.moovme.Assets;

public class Asset {

    private final Assets.AssetType assetType;
    private final double price;
    boolean assetIsOcupied;

    public Asset(Assets.AssetType assetType, int precioDeAlquilerDelLote){
        this.assetType=assetType;
        this.price=precioDeAlquilerDelLote;
    }

    public void occupy(){
        assetIsOcupied=true;
    }

    public double getPrice() {
        return price;
    }

    public Assets.AssetType getAssetType() {
        return assetType;
    }
}
