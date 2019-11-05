package com.spacetech.moovme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spacetech.moovme.Repository.RepositoryAdmin;
import com.spacetech.moovme.Repository.RepositoryAsset;
import com.spacetech.moovme.Repository.RepositoryUser;
import com.spacetech.moovme.Repository.RepositoryZone;
import com.spacetech.moovme.SlidePage.Menu_activity;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.exceptions.ZoneAlreadyExistsException;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RepositoryZone repositoryZone;
    RepositoryAdmin repositoryAdmin;
    RepositoryUser repositoryUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositoryAdmin = new RepositoryAdmin();
        loadAdmin();
        if (repositoryAdmin == null){
            repositoryAdmin = new RepositoryAdmin();
        }
        repositoryUser = new RepositoryUser();

        repositoryZone = new RepositoryZone();
        loadZone();
        if (repositoryZone == null) {
            repositoryZone = new RepositoryZone();
        }



        try {
            repositoryZone.add(15,"Palermo",getApplicationContext());
        } catch (Exeptions.ZoneAlreadyExistsException e) {
            e.printStackTrace();
        }

        RepositoryAsset repositoryAsset = new RepositoryAsset();
        repositoryAsset.addAssetType(new AssetType(5,"Moto"));
        Mooveme mooveme = new Mooveme(repositoryUser,repositoryAdmin,repositoryZone,repositoryAsset);
        String name = "Fabro";

        Mooveme.registeradmin(name);
        if(Mooveme.loginadmin(name)){
            Toast.makeText(getApplicationContext(), "creado el admin",Toast.LENGTH_LONG).show();
        }
        Mooveme.register("Agus",new PhoneNumber("1"));
        mooveme.setName("Estacreadalamierda");



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getApplicationContext(),"Entro", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplication(), Menu_activity.class);
                startActivity(i);
            }
        }, 2500);

    }

    private void loadZone() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.spacetech.moovme.Mooveme", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Zone", null);
        Type type = new TypeToken<HashMap<String, Zone>>() {}.getType();
        HashMap hashMap = gson.fromJson(json, type);
        repositoryZone.setZones(hashMap);
    }
    private void loadAdmin(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.spacetech.moovme.Mooveme", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Admins",null);
        Type type = new TypeToken<HashMap<String, Administrator>>(){}.getType();
        HashMap hashMap = gson.fromJson(json,type);
        repositoryAdmin.setAdmin(hashMap);
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

