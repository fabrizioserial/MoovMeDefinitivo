package com.spacetech.moovme.userJava;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class menu_user extends AppCompatActivity {

    Spinner sp_assetType,sp_zone;
    String assetTypename;
    //Actives
    AssetType assetTypeActive;
    Zone zoneactive;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);
        sp_assetType = (Spinner) findViewById(R.id.sp_user_assets);


        ///metodos
        SpinnerAssets();
        SpinnerZone();
    }

    private void SpinnerZone() {
        HashMap zoneHashMap = Mooveme.getRepositoryZone().getCollection();
        Collection values = zoneHashMap.values();
        ArrayList<Zone> zoneArrayList = new ArrayList<Zone>(values);
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zoneArrayList);
        sp_zone.setAdapter(adapter);
        zoneactive = (Zone) sp_zone.getSelectedItem();
    }

    private void SpinnerAssets() {
        ArrayList assetTypes= Mooveme.getRepositoryAsset().getCollection();
        AssettypeAdapter adapter = new AssettypeAdapter(getApplicationContext(),assetTypes);
        sp_assetType.setAdapter(adapter);
        assetTypename = sp_assetType.getSelectedItem().toString();
        assetTypeActive = (AssetType) sp_assetType.getSelectedItem();
    }
}
