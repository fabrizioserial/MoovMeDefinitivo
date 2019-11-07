package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exeptions.PriceIsAlreadySetExeption;
import com.spacetech.moovme.Repository.ListAssetBachCodes;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PricesPerZoneTest {

    @Test
    public void testingTest(){
        System.out.println("funco");
        Assert.assertEquals(1,1);
    }

    @Test
    public void whenBuyingNewAssetBatchShouldSucced() throws PriceIsAlreadySetExeption {
        Data data=new Data("pepe");
        Administrator administrator=new Administrator(data);
        AssetType bici=new AssetType(50,"Bici");
        Zone zone=new Zone("CABA");
        ListAssetBachCodes listAssetBachCodes=new ListAssetBachCodes();
        Price price=new Price(40);

        administrator.buyBatch(bici,6,zone,listAssetBachCodes,price);
        ArrayList<AssetBatch> arrayListOfBacthContainedInZone= zone.getTotalAssetsBatchList();

        ArrayList<AssetBatch> expectedBatchList=new ArrayList<>();
        expectedBatchList.add(new AssetBatch(bici,6,1));
        Assert.assertEquals(expectedBatchList.size(),arrayListOfBacthContainedInZone.size());
    }

    @Test
    public void whenTryingToBuyNewBatchShouldFail() throws PriceIsAlreadySetExeption {
        Data data=new Data("pepe");
        Administrator administrator=new Administrator(data);
        AssetType bici=new AssetType(50,"Bici");
        Zone zone=new Zone("CABA");
        ListAssetBachCodes listAssetBachCodes=new ListAssetBachCodes();
        Price price=new Price(40);
        Price price1= new Price(50);
        administrator.buyBatch(bici,3,zone,listAssetBachCodes,price);

        boolean exeptionThrown=false;

        try {
            administrator.buyBatch(bici,6,zone,listAssetBachCodes,price);
        } catch (PriceIsAlreadySetExeption priceIsAlreadySetExeption) {
            exeptionThrown=true;
        }

        Assert.assertTrue(exeptionThrown);

    }

}