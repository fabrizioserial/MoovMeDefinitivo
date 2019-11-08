package com.spacetech.moovme.Users;


import android.widget.EditText;

import com.spacetech.moovme.Assets.AssetBatch;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Discount;
import com.spacetech.moovme.Assets.Fee;
import com.spacetech.moovme.Assets.ParkingAlreadyExistException;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Exceptions.ItemDoesNotExistException;
import com.spacetech.moovme.Exceptions.PriceIsAlreadySetException;
import com.spacetech.moovme.Exceptions.UserIsAlreadyLockedException;
import com.spacetech.moovme.Exceptions.ZoneAlreadyExistsException;
import com.spacetech.moovme.Exceptions.ZoneDoesNotExistException;
import com.spacetech.moovme.Repository.Repository;

import java.util.ArrayList;


public class Administrator extends Operators {

    private final Data data;

    public Administrator(Data data) {
        this.data=data;
    }

    public String getName() {
        return data.getName();
    }



    public void setUserLock(User user) throws UserIsAlreadyLockedException {
        user.userLocking(true);
    }

    public void registerAdmin(Repository<Administrator> repositoryAdmins, Data data) throws ElementExistException {
        repositoryAdmins.add(new Administrator(data));
    }

    public void buyBatch(AssetType assetType, int cuantity, Zone zone, int listAssetBatchCode, Fee precioDeAlquilerDelLote) throws PriceIsAlreadySetException {
        AssetBatch assetBatch =new AssetBatch(assetType,cuantity,listAssetBatchCode);
        zone.addNewBach(assetBatch,precioDeAlquilerDelLote);
    }

    public void createNewZone(Repository<Zone> zones, String name) throws ZoneAlreadyExistsException {
        try {
            zones.add(new Zone(name));
        } catch (ElementExistException elementExistExeption) {
            throw new ZoneAlreadyExistsException();
        }
    }

    public void deleteZone(Repository<Zone> zones, String zoneToDelete) throws ZoneDoesNotExistException {
        try {
            zones.remove(new Zone(zoneToDelete));
        } catch (ItemDoesNotExistException itemDoesNotExistExeption) {
            throw new ZoneDoesNotExistException();
        }
    }

    public Zone getZone(Repository<Zone> zoneRepository,EditText et_zonename)throws ZoneDoesNotExistException {
        for(Zone zone:zoneRepository.getRepository()){
            if(zone.getName().equals(et_zonename)){
              return zone;
            }
        }
        throw new ZoneDoesNotExistException();
    }

    public void createAssetType(String assetName, int points, Repository<AssetType> assetTypeRepository) {
        try {
            assetTypeRepository.add(new AssetType(points,assetName));
        } catch (ElementExistException elementExistExeption) {
            elementExistExeption.printStackTrace();
        }
    }

    public boolean equals(Object object){
        if(object instanceof Administrator){
            return ((Administrator) object).getName().equals(data.getName());
        }
        else return false;
    }
    public void addNewAssetParking(Zone zone,String name) throws ParkingAlreadyExistException {
        zone.createAssetParking(name);
    }

    public void addDiscount(Zone zone,int minimumPoints,double percentageMultiplier,AssetType assetType) throws ElementExistException {
        zone.addDiscount(new Discount(minimumPoints,percentageMultiplier),assetType);
    }

    public void giveMonthlyPrices(Repository<Zone> zones){
        for (Zone zone:zones.getRepository()) {
            zone.giveTopUsersMonthDiscount();
        }
    }
}
