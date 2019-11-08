package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exceptions.PriceIsAlreadySetExeption;
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
        Fee fee =new Fee(40);

        administrator.buyBatch(bici,6,zone,listAssetBachCodes, fee);
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
        Fee fee =new Fee(40);
        Fee fee1 = new Fee(50);
        administrator.buyBatch(bici,3,zone,listAssetBachCodes, fee);

        boolean exeptionThrown=false;

        try {
            administrator.buyBatch(bici,6,zone,listAssetBachCodes, fee);
        } catch (PriceIsAlreadySetExeption priceIsAlreadySetExeption) {
            exeptionThrown=true;
        }

        Assert.assertTrue(exeptionThrown);

    }

}