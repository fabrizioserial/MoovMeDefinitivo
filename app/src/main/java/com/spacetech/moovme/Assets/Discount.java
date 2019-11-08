package com.spacetech.moovme.Assets;


import com.spacetech.moovme.Exceptions.CantApplyDiscountException;
import com.spacetech.moovme.Points.Points;

public class Discount {

    private final int minimumPoints;
    private final double percentageMultiplier;

    public Discount(int minimumPoints, double percentageMultiplier) {
        this.minimumPoints = minimumPoints;
        this.percentageMultiplier = percentageMultiplier;
    }

    public double applyDiscount(Points points, Fee fee)throws CantApplyDiscountException {
        if(points.getPointsinIntValue()>=minimumPoints){
            return fee.getPrice()*percentageMultiplier;
        }
        else{
            throw new CantApplyDiscountException();
        }
    }

    public boolean canApplyDiscount(Points points){
        return points.getPointsinIntValue()>=minimumPoints;
    }
}
