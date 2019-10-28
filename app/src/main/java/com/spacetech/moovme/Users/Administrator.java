package com.spacetech.moovme.Users;

import android.content.Context;

import com.spacetech.moovme.Repository.ListAssetBachCodes;
import com.spacetech.moovme.Repository.RepositoryAdmin;
import com.spacetech.moovme.Repository.RepositoryAsset;
import com.spacetech.moovme.Repository.RepositoryUser;
import com.spacetech.moovme.Repository.RepositoryZone;
import com.spacetech.moovme.Assets.AssetBatch;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.exceptions.ZoneAlreadyExistsException;
import com.spacetech.moovme.exceptions.ZoneDoesNotExistException;


public class Administrator extends Operator {

    private final String name;

    public Administrator(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setUserLock(RepositoryUser userRepo, PhoneNumber aPhoneNumber, boolean lockUser){
        User userToBlock = userRepo.findUser(aPhoneNumber);
        /*
        try{

         */
            userToBlock.userLocking(lockUser);
            /*
        } catch (UserIsAlreadyLockedExeption userIsAlreadyLockedExeption) {
            if(userToBlock.getLock()==lockUser){
                userIsAlreadyLockedExeption.printStackTrace();
                //inform via pop up that user was already in that state and do nothing
            }

        }

             */
    }

    public void registerAdmin(RepositoryAdmin repositoryAdmins,String name){
        repositoryAdmins.addAdmin(new Administrator(name));
    }

    public void createNewZone(RepositoryZone repositoryZone, int zonepoints, String name, Context ctx) throws ZoneAlreadyExistsException {
        repositoryZone.add(zonepoints,name, ctx);
    }

    public void deleteZone(RepositoryZone repositoryZone,String name) throws ZoneDoesNotExistException {
        repositoryZone.delete(name);
    }

    public void buyBatch(AssetType assetType, int cuantity, Zone zone, ListAssetBachCodes listBachCodes, int precioDeAlquilerDelLote){
        AssetBatch assetBatch =new AssetBatch(assetType,cuantity,listBachCodes.createNewCode(),precioDeAlquilerDelLote);
        zone.addNewBach(assetBatch);
    }
    public void createAssetType(String name, int point, RepositoryAsset repositoryAsset){
        repositoryAsset.addAssetType(new AssetType(point,name));
    }
    //add new administrator to the abm


}