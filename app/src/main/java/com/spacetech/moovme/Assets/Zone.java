package com.spacetech.moovme.Assets;

import java.util.ArrayList;

import Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import Points.PointTable;
import Repository.Repository;
import Users.Data;

public class Zone {
    private final String name;

    private final Repository<Assets.AssetType> assetTypeRepository = new Repository<Assets.AssetType>();
    private final ArrayList<Assets.Asset> totalAssets=new ArrayList<>();
    private final Assets.Tarifario tarifario=new Assets.Tarifario();
    private final PointTable pointTable;//create in construtor or leave it like that? implement after knowing persistance
    private Assets.PointCounter pointCounter;
    private RepositoryDiscount repositoryDiscount=new RepositoryDiscount();

    public Zone(String name,PointTable pointTable){

        this.name=name;
        this.pointTable=pointTable;
        this.pointCounter=new Assets.PointCounter();
    }

    public void addNewBach(Assets.AssetBatch assetBatch) {
        assetList.add(assetBatch);
        for (Assets.Asset asset: assetBatch.getAssetList()) {
            totalAssets.add(asset);
        }
    }

    public Assets.Asset getAssetwithDesignatedType(Assets.AssetType assetType) throws AssetTypeDoesNotExistInSpecifiedZone {
        for(Assets.AssetBatch batch : assetList){
            if(batch.getType() == assetType){
                for(Assets.Asset asset: batch.getAssetList()){
                    if(!asset.assetIsOcupied){
                        return asset;
                    }
                }
            }
        }
        throw new AssetTypeDoesNotExistInSpecifiedZone();
    }

    public double calculateFee(Assets.Asset assetUsed, int points) {
        return tarifario.calculatePrice(assetUsed,repositoryDiscount.find(assetUsed.getAssetType()),points);
    }

    public void addDiscount(Assets.Discount discount){
        repositoryDiscount.add(discount);
    }

    public ArrayList<Assets.Discount> showDiscounts(){
        return repositoryDiscount.showDiscounts();
    }

    public ArrayList<Assets.Asset> getTotalAssets() {
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
