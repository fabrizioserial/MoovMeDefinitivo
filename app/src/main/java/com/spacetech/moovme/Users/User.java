package com.spacetech.moovme.Users;


import com.spacetech.moovme.Assets.Asset;
import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Fee;
import com.spacetech.moovme.Assets.Travel;
import com.spacetech.moovme.Exceptions.AssetTypeDoesNotExistInSpecifiedZoneException;
import com.spacetech.moovme.Exceptions.CantApplyDiscountException;
import com.spacetech.moovme.Exceptions.UserIsAlreadyLockedException;
import com.spacetech.moovme.Exceptions.UserIsAlreadyOnATripException;
import com.spacetech.moovme.Exceptions.UserIsNotInATripException;
import com.spacetech.moovme.Points.Points;


public class User extends Operators {


    private final Data data;
    private boolean isLocked=false;
    double money;
    Points points;

    Travel actualTravel=null;
    Asset assetUsed=null; //crear clase de viaje o sesion

    public User(Data data) {
        this.data =data;
        this.points=new Points(0);
        money=0;
    }

    public void userLocking(boolean lockUser) throws UserIsAlreadyLockedException {
            isLocked=lockUser;
    }

    public PhoneNumber getPhoneNumber(){
        return data.getPhoneNumber();
    }

    public String getName() {
        return data.getName();
    }

    public boolean getLock(){ //for testing
        return isLocked;
    }

    public void rentAsset(AssetParking assetParking, AssetType assetType, long expectedTime) throws AssetTypeDoesNotExistInSpecifiedZoneException, UserIsAlreadyOnATripException {
        if(actualTravel != null){
            throw new UserIsAlreadyOnATripException();
        } else {
            try {
                actualTravel = new Travel(assetParking.rentAsset(assetType), expectedTime);
            } catch (AssetTypeDoesNotExistInSpecifiedZoneException assetTypeDoesNotExistInSpecifiedZoneException) {
                actualTravel = null;
                throw new AssetTypeDoesNotExistInSpecifiedZoneException();
            }
        }
    }

    public void returnAsset(AssetParking assetParking)throws UserIsNotInATripException {
        if(actualTravel!=null){
            Fee fee = assetParking.returnAsset(actualTravel,this); //points had already been added here
            boolean wantsToApplyDiscount=false;
            if(assetParking.canApplyDiscount(actualTravel,this)){
                //TODO ask if you want to apply discount. change wantsToApplyDiscount boolean
            }
            if(wantsToApplyDiscount){
                try {
                    fee = assetParking.applyDiscount(actualTravel,this,fee);
                } catch (CantApplyDiscountException cantApplyDiscountException) {
                    throw new RuntimeException("Situacion imposible ya que se chequeo antes que esto sea posible");
                }
            }
            money= money-fee.getPrice(); //TODO spend money from user
            actualTravel=null;
        }
        else{
            throw new UserIsNotInATripException();
        }
    }


    public boolean equals(Object o1){
        if(o1 instanceof User){
            return ((User) o1).getPhoneNumber().getNumber()==data.getPhoneNumber().getNumber();
        }
        else return false;
    }

    public Data getData() {
        return data;
    }

    public void addPoints(Points calculateAquiredPoints) {
        points.add(calculateAquiredPoints);
    }

    public Points getPoints() {
        return points;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public Travel getActualTravel() {
        return actualTravel;
    }
}

