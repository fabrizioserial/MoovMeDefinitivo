package com.spacetech.moovme.Repository;

import com.spacetech.moovme.Assets.AssetType;

import java.util.ArrayList;

public class RepositoryAsset implements IRepository<ArrayList>{
    private ArrayList<AssetType> assetTypes;

    public RepositoryAsset(){
        this.assetTypes = new ArrayList<>();
    }

    public void addAssetType(AssetType assetType){
        assetTypes.add(assetType);
    }
    @Override
    public ArrayList<AssetType> getCollection(){
        return assetTypes;
    }

}
