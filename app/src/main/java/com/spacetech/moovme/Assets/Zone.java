package com.spacetech.moovme.Assets;

import com.spacetech.moovme.Exceptions.AssetTypeDoesNotExistInSpecifiedZoneException;
import com.spacetech.moovme.Exceptions.CantApplyDiscountException;
import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Exceptions.PriceIsAlreadySetException;
import com.spacetech.moovme.Points.PointCounter;
import com.spacetech.moovme.Points.PointTable;
import com.spacetech.moovme.Points.RankingInPointTable;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Zone {
    private final String name; //nombre de zona
    private final ArrayList<AssetBatch> totalAssetsBatchList; //guarda los paquetes de assets que tiene
    private Tarifario tarifario;//guarda el tarifario que lleva cuenta de los precios
    private final PointTable pointTable;//tabla de puntajes
    private HashMap<AssetType, Discount> discountOrganizedByAssetType;//hashmap que guarda descuentos por tipos de asset
    private HashSet<Data> usersWithWinnerDiscount;//lista de usuarios que tienen el descuento por haber salido primeros en la tabla de puntos en el mes
    private PointCounter pointCounter;//contador de puntos que saca la cuenta de cuantos puntos ganaste en el viaje


    public Zone(String name){
        discountOrganizedByAssetType = new HashMap<>(); //constructor inicializa variables de arriba
        this.name=name;
        this.pointTable=new PointTable();
        this.pointCounter=new PointCounter();
        tarifario = new Tarifario();
        totalAssetsBatchList =new ArrayList<>();
        usersWithWinnerDiscount=new HashSet<>();
    }

    public void addNewBach(AssetBatch assetBatch, Fee precioDeAlquilerDelLote) throws PriceIsAlreadySetException {
        //metodo para agregar un nuevo paquete de assets
        tarifario.addAssetPricePerZone(assetBatch.getType(),precioDeAlquilerDelLote);//agrega los precios del asset a la zona y explota si ya tenia otro
        totalAssetsBatchList.add(assetBatch);//an;ade el asset a la lista
    }


    public Fee returnAsset(Travel actalTravel, User user) { //metodo para devolver el asset a la zona

        int i = 0;
        //esta parte es para identificar cual es el asset que estaba ocupado y desocuparlo
        ArrayList<Asset> posibleAssetsUsed=new ArrayList<>();
        for (AssetBatch assetBatch:totalAssetsBatchList) {
            if(assetBatch.getType().equals(actalTravel.getAsset().getAssetType())){

                posibleAssetsUsed=assetBatch.getAssetList();
            }
        }
        boolean wasReturned=false;
        for (Asset asset:posibleAssetsUsed) {
            if(!asset.assetIsOcupied){
                asset.returnAsset();
                i++;
                wasReturned=true;
            }
        }
        if(!wasReturned) throw new RuntimeException("Algo malo paso" + String.valueOf(i) );

        //actualizar puntos en tabla y a;adir puntos al usuario
        pointTable.updateScore(pointCounter.calculateAquiredPoints(actalTravel),user.getData());
        user.addPoints(pointCounter.calculateAquiredPoints(actalTravel));
        return tarifario.calculatePrice(actalTravel.getAsset().getAssetType()); //devuelve la tarifa de cuanto le salio sin ningun descuento aplicado
    }

    public Fee returnAssetTimeTest(Travel actalTravel, User user,int time) { //mismo que el anterior pero con tiempo ajustable
        ArrayList<Asset> posibleAssetsUsed=null;
        for (AssetBatch assetBatch:this.totalAssetsBatchList) {
            if(assetBatch.getType().equals(actalTravel.getAsset().getAssetType())){
                posibleAssetsUsed=assetBatch.getAssetList();
            }
        }
        boolean wasReturned=false;
        for (Asset asset:posibleAssetsUsed) {
            if(asset.assetIsOcupied&&!wasReturned){
                asset.returnAsset();
                wasReturned=true;
            }
        }
        if(!wasReturned) throw new RuntimeException("Algo malo paso");

        pointTable.updateScore(pointCounter.calculateAquiredPointsTimeTest(actalTravel,time),user.getData());
        user.addPoints(pointCounter.calculateAquiredPointsTimeTest(actalTravel,time));
        return tarifario.calculatePrice(actalTravel.getAsset().getAssetType());
    }

    public void addDiscount(Discount discount, AssetType assetType) throws ElementExistException {
        discountOrganizedByAssetType.put(assetType,discount);
    }


    public boolean canApplyDiscount(Travel actualTravel, User user) {
        if(usersWithWinnerDiscount.contains(user.getData())){//se fija si el usuario esta en la tabla de ganadores
            return true;
        }
        else if(discountOrganizedByAssetType.containsKey(actualTravel.getAsset().getAssetType())){//se fija si hay un descuento para el tipo de asset de su viaje
            return discountOrganizedByAssetType.get(actualTravel.getAsset().getAssetType()).canApplyDiscount(user.getPoints());
        }
        else return false;
    }

    public Fee applyDiscount(Travel actualTravel, User user, Fee fee) throws CantApplyDiscountException {
        if(usersWithWinnerDiscount.contains(user.getData())){
            usersWithWinnerDiscount.remove(user.getData()); //saca al usuario de la tabla de ganadores porque ya uso su descuento
            return new Fee(fee.getPrice()*0.5);//devuelve una tarifa de cuanto le sale si se aplica el descuento
        }
        else if(discountOrganizedByAssetType.containsKey(actualTravel.getAsset().getAssetType())){
            return new Fee(discountOrganizedByAssetType.get(actualTravel.getAsset().getAssetType()).applyDiscount(user,fee));//devuelve una tarifa de cuanto le sale si se aplica el descuento por asset
        }
        else return fee;
    }

    public ArrayList<RankingInPointTable> getTop10Leaders(){
        return pointTable.getTopLeaders();
    }

    public void giveTopUsersMonthDiscount(){ //guarda a los ganadores mensuales en su lista de ganadores
        ArrayList<RankingInPointTable> topLeaders=getTop10Leaders();
        for (RankingInPointTable ranking:topLeaders) {
            usersWithWinnerDiscount.add(ranking.getData());
        }
    }

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

    public Asset getAssetwithDesignatedType(AssetType assetType) throws AssetTypeDoesNotExistInSpecifiedZoneException {//metodo usado para alquilar un asset
        for(AssetBatch batch : totalAssetsBatchList){
            if(batch.getType().equals(assetType)){
                for(Asset asset: batch.getAssetList()){
                    if(!asset.assetIsOcupied){
                        asset.occupy();
                        return asset;
                    }
                }
            }
        }
        throw new AssetTypeDoesNotExistInSpecifiedZoneException();
    }

    public ArrayList<RankingInPointTable> getRankings(){
        return pointTable.getRankings();
    }
}
