package com.spacetech.moovme.Assets;

import java.util.ArrayList;

public class AssetBatch {
    private final ArrayList<Asset> assetList;
    private final Integer code;
    private final AssetType type;


    public AssetBatch(AssetType assetType, int cuantity, Integer newCode){
        assetList = new ArrayList<Asset>();
        for (int i = 0; i <cuantity; i++) {
            assetList.add(new Asset(assetType));
        }
        this.code=newCode;
        this.type=assetType;
    }

    public ArrayList<Asset> getAssetList() {
        return assetList;
    }

    public Integer getCode() {
        return code;
    }

    public AssetType getType() {
        return type;
    }

    public boolean equals(Object o){
        AssetBatch assetBatch = (AssetBatch) o;
        return this.getType().getName().equals(assetBatch.getType().getName());
    }
}
