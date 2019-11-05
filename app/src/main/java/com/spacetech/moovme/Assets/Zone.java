package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;

import Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import Points.PointTable;
import Users.Data;

public class Zone {
    private final String name;

    private final Repository<AssetType> assetTypeRepository = new Repository<AssetType>();
    private final ArrayList<AssetBatch> totalAssetsBatchList =new ArrayList<>();
    private final Assets.Tarifario tarifario=new Assets.Tarifario();
    private final PointTable pointTable;//create in construtor or leave it like that? implement after knowing persistance
    private HashMap<AssetType, Discount> discountOrganizedByAssetType= new HashMap<>();
    private PointCounter pointCounter;

    public Zone(String name,PointTable pointTable){

        this.name=name;
        this.pointTable=pointTable;
        this.pointCounter=new PointCounter();
    }

    public void addNewBach(AssetBatch assetBatch) {

        totalAssetsBatchList.add(assetBatch);
    }

    public Asset getAssetwithDesignatedType(AssetType assetType) throws AssetTypeDoesNotExistInSpecifiedZone {
        for(AssetBatch batch : totalAssetsBatchList){
            if(batch.getType() == assetType){
                for(Asset asset: batch.getAssetList()){
                    if(!asset.assetIsOcupied){
                        return asset;
                    }
                }
            }
        }
        throw new AssetTypeDoesNotExistInSpecifiedZone();
    }

    public double calculateFee(Asset assetUsed, int points) {

        return tarifario.calculatePrice(assetUsed,discountOrganizedByAssetType.get(assetUsed.getAssetType()),points);
    }

    public void addDiscount(Discount discount, AssetType assetType) throws Exeptions.ElementExistExeption {
        discountOrganizedByAssetType.put(assetType,discount);
    }

    public ArrayList<Assets.Asset> getTotalAssets() {
        ArrayList<Assets.Asset> totalAssets=new ArrayList<>();
        for (Assets.AssetBatch assetBatch:totalAssetsBatchList) {
            totalAssets.addAll(assetBatch.getAssetList());
        }
        return totalAssets;
    }

    public Integer actualizarPuntos(Assets.Travel actualTravel, Data data, Integer points) {
        Integer aquiredPoints=points+pointCounter.calculateAquiredPoints(actualTravel);
        pointTable.updateScore(aquiredPoints,data);
        return aquiredPoints;
    }

    /*
    public ArrayList<NameAndScore> getTop10Leaders(){
        //TODO return top 10Leaders
        //this method can be called from user
    }

    public void giveTopUsersMonthDiscount(){
        //TODO this method is called from admin
        //then it tells user to have a discount
        //user.givemonthDiscount()
        //this activates a boolean in user that determinates if he has a 50 discount in next purchase
    }
    */

    public String getName() {
        return name;
    }
}
