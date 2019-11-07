package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import com.spacetech.moovme.Exeptions.ElementExistExeption;
import com.spacetech.moovme.Exeptions.PriceIsAlreadySetExeption;
import com.spacetech.moovme.Points.PointCounter;
import com.spacetech.moovme.Points.PointTable;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Zone {
    private final String name;

    private final Repository<AssetType> assetTypeRepository = new Repository<>();
    private final ArrayList<AssetBatch> totalAssetsBatchList =new ArrayList<>();
    private final Tarifario tarifario;
    private final PointTable pointTable;//create in construtor or leave it like that? implement after knowing persistance
    private HashMap<AssetType, Discount> discountOrganizedByAssetType= new HashMap<>();
    private PointCounter pointCounter;

    public Zone(String name){

        this.name=name;
        this.tarifario = new Tarifario();
        this.pointTable=new PointTable();
        this.pointCounter=new PointCounter();
    }

    public void addNewBach(AssetBatch assetBatch, Price precioDeAlquilerDelLote) throws PriceIsAlreadySetExeption {
        tarifario.addAssetPricePerZone(assetBatch.getType(),precioDeAlquilerDelLote);
        totalAssetsBatchList.add(assetBatch);
    }

    public Asset getAssetwithDesignatedType(AssetType assetType) throws AssetTypeDoesNotExistInSpecifiedZone {
        for(AssetBatch batch : totalAssetsBatchList){
            if(batch.getType().equals(assetType)){
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

    public void addDiscount(Discount discount, AssetType assetType) throws ElementExistExeption {
        discountOrganizedByAssetType.put(assetType,discount);
    }

    public ArrayList<Asset> getTotalAssets() {
        ArrayList<Asset> totalAssets=new ArrayList<>();
        for (AssetBatch assetBatch:totalAssetsBatchList) {
            totalAssets.addAll(assetBatch.getAssetList());
        }
        return totalAssets;
    }
    public void addAssetParking(){

    }

    public Integer actualizarPuntos(Travel actualTravel, Data data, Integer points) {
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

    public ArrayList<AssetBatch> getTotalAssetsBatchList() {
        return totalAssetsBatchList;
    }

    public boolean equals(Object object){
        if(object instanceof Zone){
            return ((Zone) object).getName().equals(name);
        }
        else return false;
    }

}
