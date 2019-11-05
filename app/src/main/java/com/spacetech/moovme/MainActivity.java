package com.spacetech.moovme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.SlidePage.Menu_activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import Users.Administrator;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Repository> repositories = new ArrayList<>();
        repositories.add(new Repository());



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
        Type type = new TypeToken<HashMap<String, Assets.Zone>>() {}.getType();
        HashMap hashMap = gson.fromJson(json, type);
        repositoryZone.setZones(hashMap);
    }
    private void loadAdmin(){
        SharedPreferences sharedPreferences = getSharedPreferences("com.spacetech.moovme.Mooveme", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Admins",null);
        Type type = new TypeToken<HashMap<String, Users.Administrator>>(){}.getType();
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

