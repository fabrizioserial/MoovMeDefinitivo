package com.spacetech.moovme.Assets;


import com.spacetech.moovme.Exeptions.CantApplyDiscountExeption;

public class Discount {

    private final int minimumPoints;
    private final double percentageMultiplier;
    private AssetType assetType;

    public Discount(int minimumPoints, double percentageMultiplier, AssetType assetType) {
        this.minimumPoints = minimumPoints;
        this.percentageMultiplier = percentageMultiplier;
        this.assetType = assetType;
    }

    public double applyDiscount(AssetType assetType, int points, double fee)throws CantApplyDiscountExeption {
        if(points>=minimumPoints){
            return fee*percentageMultiplier;
        }
        else{
            throw new CantApplyDiscountExeption();
        }

    }

    public AssetType getAssetType() {
        return assetType;
    }

    public boolean equals(Object object){
        if(object instanceof Discount){
            return ((Discount) object).getAssetType().equals(assetType);
        }
        else return false;
    }
}
