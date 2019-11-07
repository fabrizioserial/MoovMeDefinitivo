package com.spacetech.moovme.Assets;

public class Asset {

    private final AssetType assetType;
    boolean assetIsOcupied;

    public Asset(AssetType assetType){
        this.assetType=assetType;
    }

    public void occupy(){
        assetIsOcupied=true;
    }

    public AssetType getAssetType() {
        return assetType;
    }
}
