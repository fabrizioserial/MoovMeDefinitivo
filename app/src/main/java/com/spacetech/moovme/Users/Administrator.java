package Users;


import Assets.AssetBatch;
import Assets.AssetType;
import Assets.Zone;
import Points.PointTable;


public class Administrator extends Users.Operators {

    private final Users.Data data;

    public Administrator(Users.Data data) {
        this.data=data;
    }

    public String getName() {
        return data.getName();
    }



    public void setUserLock(Users.User user, boolean lockUser) throws Exeptions.UserIsAlreadyLockedExeption {
        user.userLocking(true);
    }

    public void registerAdmin(Repository.Repository<Administrator> repositoryAdmins, Users.Data data){
        repositoryAdmins.add(new Administrator(data));
    }

    public void buyBatch(AssetType assetType, int cuantity, Zone zone, Repository.ListAssetBachCodes listBachCodes, int precioDeAlquilerDelLote){
        AssetBatch assetBatch =new AssetBatch(assetType,cuantity,listBachCodes.createNewCode(),precioDeAlquilerDelLote);
        zone.addNewBach(assetBatch);
    }

    public void createNewZone(Repository.Repository<Zone> zones, String name, PointTable pointTable) throws  Exeptions.ElementExistExeption {
        zones.add(new Zone(name,pointTable));
    }

    public void deleteZone(Repository.Repository<Zone> zones, Zone zoneToDelete) throws Exeptions.ItemDoesNotExistExeption {
        zones.remove(zoneToDelete);
    }
}
