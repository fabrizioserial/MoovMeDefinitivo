package com.spacetech.moovme.userJava;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.R;

import java.util.ArrayList;

public class menu_user extends AppCompatActivity {

    Spinner sp_assetType,sp_zone;
    String assetTypename;
    //Actives
    AssetType assetTypeActive;
    Zone zoneactive;
    Mooveme mooveme;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);
        sp_assetType = (Spinner) findViewById(R.id.sp_user_assets);
        mooveme = Persistence.loadMoovme(getApplicationContext());

        ///metodos
        SpinnerAssets();
        SpinnerZone();
    }

    private void SpinnerZone() {
        ArrayList<Zone> zoneArrayList = mooveme.getZoneRepository().getRepository();
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zoneArrayList);
        sp_zone.setAdapter(adapter);
        zoneactive = (Zone) sp_zone.getSelectedItem();


    }

    private void SpinnerAssets() {

        ArrayList<AssetType> assetTypes= mooveme.getAssetTypeRepository().getRepository();
        AssettypeAdapter adapter = new AssettypeAdapter(getApplicationContext(),assetTypes);
        sp_assetType.setAdapter(adapter);
        assetTypename = sp_assetType.getSelectedItem().toString();
        assetTypeActive = (AssetType) sp_assetType.getSelectedItem();

    }
}
