package com.spacetech.moovme.Assets;

import java.util.ArrayList;

public class AssetBatch {
    private final ArrayList<Asset> assetList;
    private final Integer code;
    private final Assets.AssetType type;


    public AssetBatch(Assets.AssetType assetType, int cuantity, Integer newCode, int precioDeAlquilerDelLote){
        assetList = new ArrayList<Asset>();
        for (int i = 0; i <cuantity; i++) {
            assetList.add(new Asset(assetType,precioDeAlquilerDelLote));
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

    public Assets.AssetType getType() {
        return type;
    }
}
