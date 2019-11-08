package com.spacetech.moovme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.R;

import java.util.ArrayList;

public class AssetParkingAdapter extends ArrayAdapter<AssetParking> {
    public AssetParkingAdapter(Context ctx, ArrayList<AssetParking> assetTypes){
        super(ctx,0,assetTypes);
    }
    public View getView(int position, View converview, ViewGroup parents){
        return initview(position,converview,parents);
    }
    public View getDropDownView(int position, View converview, ViewGroup parents){
        return initview(position,converview,parents);
    }
    private View initview(int position, View converview, ViewGroup parents){
        if(converview == null){
            converview = LayoutInflater.from(getContext()).inflate(R.layout.spn_item_assettype,parents,false);
        }
        TextView textView = converview.findViewById(R.id.item_name_assettype_id);
        AssetParking assetParking = getItem(position);
        if(assetParking != null){
            textView.setText(assetParking.getName());
        }

        return converview;
    }
}
