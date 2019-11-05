package com.spacetech.moovme;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

public class  Persistence {

    public static Mooveme loadMoovme(Context ctx){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Mooveme",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Mooveme", null);
        Type type = new TypeToken<Mooveme>() {}.getType();
        Mooveme mooveme = gson.fromJson(json, type);
        return mooveme;
    }

    public static void saveInformation(Context ctx,Mooveme mooveme){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences("Mooveme", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mooveme);
        editor.putString("Mooveme",json);
        editor.apply();
    }
 }
