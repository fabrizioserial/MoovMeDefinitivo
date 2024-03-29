package com.spacetech.moovme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.Assets.Asset;
import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.SlidePage.Menu_activity;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.User;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Repository<Administrator> administratorRepository = new Repository<>();
        Repository<User> usersRepository  = new Repository<>();
        Repository<Zone> zoneRepository = new Repository<>();
        Repository<Asset> assetRepository = new Repository<>();
        Repository<AssetType> assetTypeRepository = new Repository<>();
        Repository<AssetParking> assetParkingRepository = new Repository<>();

        Mooveme mooveme = new Mooveme();

        try {
            administratorRepository.add(new Administrator(new Data("admin")));
            assetTypeRepository.add(new AssetType(15,"auto"));
        } catch (ElementExistException elementExistException) {
            elementExistException.printStackTrace();
        }


        mooveme.addAssetParkingRepository(assetParkingRepository);
        mooveme.addAssetRepository(assetRepository);
        mooveme.addAdminRepository(administratorRepository);
        mooveme.addUserRepository(usersRepository);
        mooveme.addZoneRepository(zoneRepository);
        mooveme.addAssetTypeRepository(assetTypeRepository);



        if(Persistence.loadMoovme(this)!= null){
            mooveme = Persistence.loadMoovme(this);
        }
        Persistence.saveInformation(this,mooveme);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(),"v9", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplication(), Menu_activity.class);
                startActivity(i);
            }
        }, 2500);

    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}

