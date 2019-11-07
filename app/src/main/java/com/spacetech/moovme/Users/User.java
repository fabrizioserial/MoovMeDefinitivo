package com.spacetech.moovme.Users;


import com.spacetech.moovme.Assets.Asset;
import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Travel;
import com.spacetech.moovme.Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import com.spacetech.moovme.Exeptions.UserIsAlreadyLockedExeption;
import com.spacetech.moovme.Exeptions.UserIsNotInATripException;
import com.spacetech.moovme.Points.PointsStoredInUserForEachZone;


public class User extends Operators {


    private final Data data;
    private boolean isLocked=false;
    PointsStoredInUserForEachZone points;

    Travel actualTravel=null;
    Asset assetUsed=null; //crear clase de viaje o sesion

    public User(Data data) {
        this.points=new PointsStoredInUserForEachZone();
        this.data =data;
    }

    public void userLocking(boolean lockUser) throws UserIsAlreadyLockedExeption {
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

    public void rentAsset(AssetParking assetParking, AssetType assetType, long expectedTime)throws AssetTypeDoesNotExistInSpecifiedZone {
        Travel travel=new Travel(assetParking.rentAsset(assetType),new Timer(System.nanoTime()),expectedTime);
    }

    public double returnAsset(AssetParking assetParking)throws UserIsNotInATripException {
        if(actualTravel!=null){
            //TODO parking add points to user and return fee also actualize in score table
            //TODO ask to assetParking if you can apply discount and aplly if user wants
            double totalFee = assetParking.returnAsset(actualTravel,points.getPoints(assetParking.getZone()));
            points.add(assetParking.ganarPuntos(actualTravel,data,points.getPoints(assetParking.getZone())),assetParking.getZone());
            actualTravel=null;
            return  totalFee;
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

}

