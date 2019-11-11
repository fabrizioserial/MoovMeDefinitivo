package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Users.User;

public class Asset {

    private final AssetType assetType;
    boolean assetIsOcupied;
    User userThatIsUsing;

    public Asset(AssetType assetType){
        this.assetType=assetType;
        assetIsOcupied = false;
    }

    public void occupy(){
        assetIsOcupied=true;
    }

    public void returnAsset(){ assetIsOcupied=false; }

    public boolean isOcupied(){
        return assetIsOcupied;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setUser(User user) {
        this.userThatIsUsing = user;
    }
}
