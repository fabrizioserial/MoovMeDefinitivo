package com.spacetech.moovme.Assets;

public class AssetTypePerFee {
    Fee fee;
    AssetType assetType;

    public AssetTypePerFee(AssetType assetType, Fee fee) {
        this.assetType = assetType;
        this.fee = fee;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }
}
