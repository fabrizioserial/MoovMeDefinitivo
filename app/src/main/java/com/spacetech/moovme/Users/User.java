package com.spacetech.moovme.Users;


import Exeptions.UserIsAlreadyLockedExeption;
import Exeptions.UserIsNotInATripException;
import Points.PointsStoredInUserForEachZone;


public class User extends Users.Operators {


    private final Users.Data data;
    private boolean isLocked=false;
    PointsStoredInUserForEachZone points;

    Assets.Travel actualTravel=null;
    Assets.Asset assetUsed=null; //crear clase de viaje o sesion

    public User(Users.Data data) {
        this.points=new PointsStoredInUserForEachZone();
        this.data =data;
    }

    public void userLocking(boolean lockUser) throws UserIsAlreadyLockedExeption {
            isLocked=lockUser;
    }

    public Users.PhoneNumber getPhoneNumber(){
        return data.getPhoneNumber();
    }

    public String getName() {
        return data.getName();
    }

    public boolean getLock(){ //for testing
        return isLocked;
    }

    public void rentAsset(Assets.AssetParking assetParking, Assets.AssetType assetType, long expectedTime){
        Assets.Travel travel=new Assets.Travel(assetParking.rentAsset(assetType),new Users.Timer(System.nanoTime()),expectedTime);
    }

    public double returnAsset(Assets.AssetParking assetParking)throws UserIsNotInATripException {
        if(actualTravel!=null){
            //boolean returnredAtRightTime=tripTimer.compareTime(ExpectedTime);
            double totalFee = assetParking.returnAsset(actualTravel,points.getPoints(assetParking.getZone()));
            points.add(assetParking.ganarPuntos(actualTravel,data,points.getPoints(assetParking.getZone())),assetParking.getZone());
            //TODO add points when asset is returned
            actualTravel=null;
            return  totalFee;
        }
        else{
            throw new UserIsNotInATripException();
        }
    }

}

