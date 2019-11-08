package com.spacetech.moovme.Assets;


import com.spacetech.moovme.Exceptions.CantApplyDiscountExeption;
import com.spacetech.moovme.Points.Points;

public class Discount {

    private final int minimumPoints;
    private final double percentageMultiplier;
    private AssetType assetType;

    public Discount(int minimumPoints, double percentageMultiplier, AssetType assetType) {
        this.minimumPoints = minimumPoints;
        this.percentageMultiplier = percentageMultiplier;
        this.assetType = assetType;
    }

    public double applyDiscount(Points points, Fee fee)throws CantApplyDiscountExeption {
        if(points.getPointsinIntValue()>=minimumPoints){
            return fee.getPrice()*percentageMultiplier;
        }
        else{
            throw new CantApplyDiscountExeption();
        }
    }

    public boolean canApplyDiscount(Points points){
        return points.getPointsinIntValue()>=minimumPoints;
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
