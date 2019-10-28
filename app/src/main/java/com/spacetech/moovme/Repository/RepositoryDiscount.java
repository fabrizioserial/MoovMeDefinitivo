package com.spacetech.moovme.Repository;


import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Discount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RepositoryDiscount implements IRepository<Discount>{

    HashMap<AssetType, Discount> discounts;
    ArrayList<AssetType> assetTypesInRepository;

    public RepositoryDiscount(){
        discounts=new HashMap<>();
        assetTypesInRepository=new ArrayList<>();
    }

    @Override
    public List<Discount> getList() {
        return null;
    }

    public void add(Discount discount){
        discounts.put(discount.getAssetType(),discount);
        assetTypesInRepository.add(discount.getAssetType());
    }

    public Discount find(AssetType assetType){
        return discounts.get(assetType);
    }

    public ArrayList<Discount> showDiscounts(){
        ArrayList<Discount> discountsToShow=new ArrayList<>();
        for (AssetType assetType:assetTypesInRepository) {
            discountsToShow.add(discounts.get(assetType));
        }
        return discountsToShow;
    }

}