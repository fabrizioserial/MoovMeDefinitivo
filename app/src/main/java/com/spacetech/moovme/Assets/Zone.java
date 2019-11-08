package com.spacetech.moovme.Assets;

import android.service.notification.NotificationListenerService;

import com.spacetech.moovme.Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import com.spacetech.moovme.Exeptions.CantApplyDiscountExeption;
import com.spacetech.moovme.Exeptions.ElementExistExeption;
import com.spacetech.moovme.Exeptions.PriceIsAlreadySetExeption;
import com.spacetech.moovme.Points.PointCounter;
import com.spacetech.moovme.Points.PointTable;
import com.spacetech.moovme.Points.RankingInPointTable;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Zone {
    private final String name;
    private final ArrayList<AssetBatch> totalAssetsBatchList =new ArrayList<>();
    private final Tarifario tarifario=new Tarifario();
    private final PointTable pointTable;//create in construtor or leave it like that? implement after knowing persistance
    private HashMap<AssetType, Discount> discountOrganizedByAssetType= new HashMap<>();
    private ArrayList<Data> usersWithWinnerDiscount;
    private PointCounter pointCounter;

    public Zone(String name){
        this.name=name;
        this.pointTable=new PointTable();
        this.pointCounter=new PointCounter();
    }

    public void addNewBach(AssetBatch assetBatch, Fee precioDeAlquilerDelLote) throws PriceIsAlreadySetExeption {
        tarifario.addAssetPricePerZone(assetBatch.getType(),precioDeAlquilerDelLote);
        totalAssetsBatchList.add(assetBatch);
    }


    public Fee returnAsset(Travel actalTravel, User user) {
        pointTable.updateScore(pointCounter.calculateAquiredPoints(actalTravel),user.getData());
        user.addPoints(pointCounter.calculateAquiredPoints(actalTravel));
        return tarifario.calculatePrice(actalTravel.getAsset().getAssetType());
    }

    public void addDiscount(Discount discount, AssetType assetType) throws ElementExistExeption {
        discountOrganizedByAssetType.put(assetType,discount);
    }


    public boolean canApplyDiscount(Travel actualTravel, User user) {
        if(!usersWithWinnerDiscount.contains(user.getData())){
            return discountOrganizedByAssetType.get(actualTravel.getAsset().getAssetType()).canApplyDiscount(user.getPoints());
        }
        else return false;
    }

    public Fee applyDiscount(Travel actualTravel, User user, Fee fee) throws CantApplyDiscountExeption {
        if(!usersWithWinnerDiscount.contains(user.getData())){
            return new Fee(fee.getPrice()*0.5);
        }
        else if(discountOrganizedByAssetType.containsKey(actualTravel.getAsset().getAssetType())){
            return new Fee(discountOrganizedByAssetType.get(actualTravel.getAsset().getAssetType()).applyDiscount(user.getPoints(),fee));
        }
        else return fee;
    }

    public ArrayList<RankingInPointTable> getTop10Leaders(){
        return pointTable.getTopLeaders();
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
    public ArrayList<Asset> getTotalAssets() {
        ArrayList<Asset> totalAssets=new ArrayList<>();
        for (AssetBatch assetBatch:totalAssetsBatchList) {
            totalAssets.addAll(assetBatch.getAssetList());
        }
        return totalAssets;
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

}
