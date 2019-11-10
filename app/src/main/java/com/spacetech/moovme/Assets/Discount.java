package com.spacetech.moovme.Assets;


import com.spacetech.moovme.Exceptions.CantApplyDiscountException;
import com.spacetech.moovme.Points.Points;
import com.spacetech.moovme.Users.User;

public class Discount {

    private final int minimumPoints;
    private final double percentageMultiplier;

    public Discount(int minimumPoints, double percentageMultiplier) {
        this.minimumPoints = minimumPoints;
        this.percentageMultiplier = percentageMultiplier;
    }

    public double applyDiscount(User user, Fee fee)throws CantApplyDiscountException {
        if(user.getPoints().getPointsinIntValue()>=minimumPoints){
            user.removePoints(minimumPoints);
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
