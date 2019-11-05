package Assets;

import Exeptions.CantApplyDiscountExeption;

public class Discount {

    private final int minimumPoints;
    private final double percentageMultiplier;

    Discount(int minimumPoints, double discountPercentage){
        this.minimumPoints=minimumPoints;
        this.percentageMultiplier=discountPercentage/100;
    }

    public double applyDiscount(int points, double fee)throws CantApplyDiscountExeption {
        if(points>=minimumPoints){
            return fee*percentageMultiplier;
        }
        else{
            throw new CantApplyDiscountExeption();
        }

    }
}
