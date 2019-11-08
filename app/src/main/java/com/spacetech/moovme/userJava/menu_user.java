package com.spacetech.moovme.userJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.Adapters.AssetParkingAdapter;
import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.DialogException;
import com.spacetech.moovme.Exeptions.AssetTypeDoesNotExistInSpecifiedZone;
import com.spacetech.moovme.Exeptions.UserDoesntExistException;
import com.spacetech.moovme.Exeptions.UserIsNotInATripException;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;

public class menu_user extends AppCompatActivity {

    String assetTypename;
    EditText et_time;
    Button btn_rentAsset;
    //Actives
    AssetType assetTypeActive;
    Zone zoneactive;
    Mooveme mooveme;
    private User activeUser;
    private Spinner sp_assetParking,sp_assetType,sp_zone;;
    private AssetParking AssetParkingRent,AssetParkingReturn;
    private Button btn_returnAsset;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);
        sp_assetType = (Spinner) findViewById(R.id.sp_user_assets);
        sp_assetParking = (Spinner) findViewById(R.id.sp_user_parking);
        sp_zone = (Spinner) findViewById(R.id.sp_user_zone);
        mooveme = Persistence.loadMoovme(getApplicationContext());
        Intent i= getIntent();

        String numberofuser = i.getStringExtra("phonenumber");

        try {
            activeUser = mooveme.getUser(new Data("user",new PhoneNumber(Integer.parseInt(numberofuser))));
        } catch (UserDoesntExistException e) {
            DialogException.CreateDialog("User Error","User doesnt exist", getApplicationContext());
        }

        et_time = (EditText) findViewById(R.id.et_user_time);
        btn_rentAsset = (Button) findViewById(R.id.btn_user_rentAsset);

        ///metodos
        SpinnerAssets();
        SpinnerZone();
        SpinnerParkings();
        SpinnerParkingsReturn();


        btn_rentAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RenAsset();
            }
        });

        btn_returnAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnAnAsset();
            }
        });
    }

    public void RenAsset(){
        try {
            activeUser.rentAsset(AssetParkingRent,assetTypeActive,Integer.parseInt(et_time.getText().toString()));
        } catch (AssetTypeDoesNotExistInSpecifiedZone assetTypeDoesNotExistInSpecifiedZone) {
            DialogException.CreateDialog("Error","Error to rent, asset type doesnt exis tin this zone",this);
        }
    }

    public void returnAnAsset(){
        try {
            activeUser.returnAsset(AssetParkingReturn);
        } catch (UserIsNotInATripException e) {
            DialogException.CreateDialog("Error","User is not in a trip",getApplicationContext());
        }
    }

    ///SPINNERS

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

    private void SpinnerParkings() {
        ArrayList<AssetParking> assetTypes= zoneactive.getAssetParkings();
        AssetParkingAdapter adapter = new AssetParkingAdapter(getApplicationContext(),assetTypes);
        sp_assetParking.setAdapter(adapter);
        AssetParkingRent = (AssetParking) sp_assetParking.getSelectedItem();

    }
    private void SpinnerParkingsReturn() {
        ArrayList<AssetParking> assetTypes= zoneactive.getAssetParkings();
        AssetParkingAdapter adapter = new AssetParkingAdapter(getApplicationContext(),assetTypes);
        sp_assetParking.setAdapter(adapter);
        AssetParkingReturn = (AssetParking) sp_assetParking.getSelectedItem();
    }
}
