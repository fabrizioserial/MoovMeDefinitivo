package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exceptions.AssetTypeDoesNotExistInSpecifiedZoneException;
import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Exceptions.PriceIsAlreadySetException;
import com.spacetech.moovme.Exceptions.UserCantStartNewTrip;
import com.spacetech.moovme.Exceptions.UserIsNotInATripException;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Repository.ListAssetBachCodes;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class AssetTests {

    @Test
    public void testingTest(){
        System.out.println("funco");
        Assert.assertEquals(1,1);
    }

    @Test
    public void whenBuyingNewAssetBatchShouldSucced() throws PriceIsAlreadySetException {
        Data data=new Data("pepe");
        Administrator administrator=new Administrator(data);
        AssetType bici=new AssetType(50,"Bici");
        Zone zone=new Zone("CABA");
        ListAssetBachCodes listAssetBachCodes=new ListAssetBachCodes();
        Fee fee =new Fee(40);

        administrator.buyBatch(bici,6,zone,listAssetBachCodes.createNewCode(), fee);
        ArrayList<AssetBatch> arrayListOfBacthContainedInZone= zone.getTotalAssetsBatchList();

        ArrayList<AssetBatch> expectedBatchList=new ArrayList<>();
        expectedBatchList.add(new AssetBatch(bici,6,1));
        Assert.assertEquals(expectedBatchList.size(),arrayListOfBacthContainedInZone.size());
    }

    @Test
    public void whenTryingToBuyNewBatchShouldFail() throws PriceIsAlreadySetException {
        Data data=new Data("pepe");
        Administrator administrator=new Administrator(data);
        AssetType bici=new AssetType(50,"Bici");
        Zone zone=new Zone("CABA");
        ListAssetBachCodes listAssetBachCodes=new ListAssetBachCodes();
        Fee fee =new Fee(40);
        Fee fee1 = new Fee(50);
        administrator.buyBatch(bici,3,zone,listAssetBachCodes.createNewCode(), fee);

        boolean exeptionThrown=false;

        try {
            administrator.buyBatch(bici,6,zone,listAssetBachCodes.createNewCode(), fee);
        } catch (PriceIsAlreadySetException e) {
            exeptionThrown=true;
        }

        Assert.assertTrue(exeptionThrown);

    }

    @Test
    public void whenRentingANewAssetShouldsucced() throws UserCantStartNewTrip, AssetTypeDoesNotExistInSpecifiedZoneException, PriceIsAlreadySetException {
        Data data=new Data("pepe");
        Administrator administrator=new Administrator(data);
        AssetType bici=new AssetType(50,"Bici");
        Zone zone=new Zone("CABA");
        ListAssetBachCodes listAssetBachCodes=new ListAssetBachCodes();
        Fee fee =new Fee(40);
        administrator.buyBatch(bici,6,zone,listAssetBachCodes.createNewCode(), fee);
        AssetParking assetParking=new AssetParking("puesto1",zone);
        User user1=new User(new Data("agustin",new PhoneNumber(420)));

        user1.rentAsset(assetParking,bici,50);

        Assert.assertNotEquals(user1.getActualTravel(),null);


    }

    @Test
    public void whenRentingANonExistingAssetShouldFail(){
        AssetType bici=new AssetType(50,"Bici");
        Zone zone=new Zone("CABA");
        AssetParking assetParking=new AssetParking("puesto1",zone);
        User user1=new User(new Data("agustin",new PhoneNumber(420)));

        boolean exeptionwasThrown=false;

        try {
            user1.rentAsset(assetParking,bici,50);
        } catch (AssetTypeDoesNotExistInSpecifiedZoneException assetTypeDoesNotExistInSpecifiedZone) {
            exeptionwasThrown=true;
        } catch (UserCantStartNewTrip e) {
            e.printStackTrace();
        }

        Assert.assertTrue(exeptionwasThrown);
    }

    @Test
    public void whenReturningAssetShouldAddPintsInPointTable() throws PriceIsAlreadySetException, ElementExistException, UserCantStartNewTrip, AssetTypeDoesNotExistInSpecifiedZoneException, UserIsNotInATripException {
        Data data=new Data("pepe");
        Administrator administrator=new Administrator(data);
        AssetType bici=new AssetType(2,"Bici");
        Zone zone=new Zone("CABA");
        ListAssetBachCodes listAssetBachCodes=new ListAssetBachCodes();
        Fee fee =new Fee(40);
        administrator.buyBatch(bici,6,zone,listAssetBachCodes.createNewCode(), fee);
        AssetParking assetParking=new AssetParking("puseto1",zone);
        User user1=new User(new Data("agustin",new PhoneNumber(420)));
        user1.setMoney(200);
        user1.rentAsset(assetParking,bici,30);
        user1.returnAssetTimeTest(assetParking,30);

        Integer int1=72;
        Integer int2=user1.getPoints().getPointsinIntValue();
        Assert.assertEquals(int1,int2);


    }

    @Test
    public void whenCreating2AssetParkingsShouldSucced(){
        Zone zone1=new Zone("SanFernando");
        AssetParking assetParking1=new AssetParking("puesto1",zone1);
        AssetParking assetParking2=new AssetParking("puesto2",zone1);

        Assert.assertEquals(2,zone1.getAssetParkings().size());
    }


    @Test
    public void moovMe() throws ElementExistException {
        Mooveme mooveme=new Mooveme();
        Zone zone1=new Zone("SanFernando");
        AssetParking assetParking1=new AssetParking("puesto1",zone1);
        AssetParking assetParking2=new AssetParking("puesto2",zone1);
        Repository<Zone> zoneRepository=new Repository<>();
        Repository<AssetParking>assetParkingRepository=new Repository<>();

        mooveme.addAssetParkingRepository(assetParkingRepository);
        mooveme.addZoneRepository(zoneRepository);
        mooveme.addZone(zone1);

    }

}