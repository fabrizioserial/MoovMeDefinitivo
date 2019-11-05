package com.spacetech.moovme.Users;


import com.spacetech.moovme.Assets.AssetBatch;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Repository.ListAssetBachCodes;
import com.spacetech.moovme.Repository.Repository;

import Points.PointTable;


public class Administrator extends Users.Operators {

    private final Data data;

    public Administrator(Data data) {
        this.data=data;
    }

    public String getName() {
        return data.getName();
    }



    public void setUserLock(User user, boolean lockUser) throws Exeptions.UserIsAlreadyLockedExeption {
        user.userLocking(true);
    }

    public void registerAdmin(Repository<Administrator> repositoryAdmins, Data data) throws Exeptions.ElementExistExeption {
        repositoryAdmins.add(new Administrator(data));
    }

    public void buyBatch(AssetType assetType, int cuantity, Zone zone, ListAssetBachCodes listBachCodes, int precioDeAlquilerDelLote){
        AssetBatch assetBatch =new AssetBatch(assetType,cuantity,listBachCodes.createNewCode(),precioDeAlquilerDelLote);
        zone.addNewBach(assetBatch);
    }

    public void createNewZone(Repository<Zone> zones, String name, PointTable pointTable) throws  Exeptions.ElementExistExeption {
        zones.add(new Zone(name,pointTable));
    }

    public void deleteZone(Repository<Zone> zones, Zone zoneToDelete) throws Exeptions.ItemDoesNotExistExeption {
        zones.remove(zoneToDelete);
    }
}
