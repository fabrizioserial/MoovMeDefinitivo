package Users;


import Assets.AssetBatch;
import Assets.AssetType;
import Assets.Zone;
import Exeptions.*;
import Points.PointTable;
import Repository.*;

public class Administrator extends Operators{

    private final Data data;

    public Administrator(Data data) {
        this.data=data;
    }

    public String getName() {
        return data.getName();
    }



    public void setUserLock(UserManager userMananger, PhoneNumber aPhoneNumber, boolean lockUser) throws UserIsNotRegistered {
        User userToBlock=userMananger.find(aPhoneNumber);
        try{
            userToBlock.userLocking(true);
        } catch (UserIsAlreadyLockedExeption userIsAlreadyLockedExeption) {
            if(userToBlock.getLock()==lockUser){
                userIsAlreadyLockedExeption.printStackTrace();
                //inform via pop up that user was already in that state and do nothing
            }

        }
    }

    public void registerAdmin(RepositoryAdmins repositoryAdmins,Data data){
        repositoryAdmins.add(data);
    }

    public void buyBatch(AssetType assetType, int cuantity, Zone zone, ListAssetBachCodes listBachCodes, int precioDeAlquilerDelLote){
        AssetBatch assetBatch =new AssetBatch(assetType,cuantity,listBachCodes.createNewCode(),precioDeAlquilerDelLote);
        zone.addNewBach(assetBatch);
    }

    public void createNewZone(Repository<Zone> zones,String name,PointTable pointTable) throws ZoneAlreadyExistsException, ElementExistExeption {
        zones.add(new Zone(name,pointTable));
    }

    public void deleteZone(Repository<Zone> zones,Zone zoneToDelete) throws ZoneDoesNotExistException {
        zones.remove(zone);
    }
}
