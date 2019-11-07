package com.spacetech.moovme.Users;


import android.widget.EditText;

import com.spacetech.moovme.Assets.AssetBatch;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Price;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Exeptions.ElementExistExeption;
import com.spacetech.moovme.Exeptions.ItemDoesNotExistExeption;
import com.spacetech.moovme.Exeptions.PriceIsAlreadySetExeption;
import com.spacetech.moovme.Exeptions.UserIsAlreadyLockedExeption;
import com.spacetech.moovme.Exeptions.ZoneAlreadyExistsException;
import com.spacetech.moovme.Exeptions.ZoneDoesNotExistException;
import com.spacetech.moovme.Repository.Repository;


public class Administrator extends Operators {

    private final Data data;

    public Administrator(Data data) {
        this.data=data;
    }

    public String getName() {
        return data.getName();
    }



    public void setUserLock(User user, boolean lockUser) throws UserIsAlreadyLockedExeption {
        user.userLocking(true);
    }

    public void registerAdmin(Repository<Administrator> repositoryAdmins, Data data) throws ElementExistExeption {
        repositoryAdmins.add(new Administrator(data));
    }

    public void buyBatch(AssetType assetType, int cuantity, Zone zone, int listBachCodes, Price precioDeAlquilerDelLote) throws PriceIsAlreadySetExeption {
        AssetBatch assetBatch =new AssetBatch(assetType,cuantity,listBachCodes);
        zone.addNewBach(assetBatch,precioDeAlquilerDelLote);
    }

    public void createNewZone(Repository<Zone> zones, String name) throws ZoneAlreadyExistsException {
        try {
            zones.add(new Zone(name));
        } catch (ElementExistExeption elementExistExeption) {
            throw new ZoneAlreadyExistsException();
        }
    }

    public void deleteZone(Repository<Zone> zones, String zoneToDelete) throws ZoneDoesNotExistException {
        try {
            zones.remove(new Zone(zoneToDelete));
        } catch (ItemDoesNotExistExeption itemDoesNotExistExeption) {
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
        } catch (ElementExistExeption elementExistExeption) {
            elementExistExeption.printStackTrace();
        }
    }

    public boolean equals(Object object){
        if(object instanceof Administrator){
            return ((Administrator) object).getName().equals(data.getName());
        }
        else return false;
    }
}
