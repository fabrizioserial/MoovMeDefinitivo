package com.spacetech.moovme.userJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spacetech.moovme.Adapters.AssetParkingAdapter;
import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Adapters.RankingAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.DialogException;
import com.spacetech.moovme.Exceptions.AssetTypeDoesNotExistInSpecifiedZoneException;
import com.spacetech.moovme.Exceptions.UserCantStartNewTrip;
import com.spacetech.moovme.Exceptions.UserDoesntExistException;
import com.spacetech.moovme.Exceptions.UserIsNotInATripException;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.Points.Points;
import com.spacetech.moovme.Points.RankingInPointTable;
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
    Zone zoneactive,zoneRent,rankingZone;
    Mooveme mooveme;
    private User activeUser;
    private Spinner sp_assetParking,sp_assetType,sp_zone,sp_assetparkingReturn, sp_rankingZone;
    private AssetParking AssetParkingRent,AssetParkingReturn;
    private Button btn_returnAsset;
    private RecyclerView ranking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);
        sp_assetType = (Spinner) findViewById(R.id.sp_user_assets);
        sp_assetParking = (Spinner) findViewById(R.id.sp_user_parking);
        sp_zone = (Spinner) findViewById(R.id.sp_user_zone);
        sp_assetparkingReturn = (Spinner) findViewById(R.id.spn_user_return_parking);
        sp_rankingZone = (Spinner) findViewById(R.id.sp_user_ranking_zone);

        mooveme = Persistence.loadMoovme(getApplicationContext());
        Intent i= getIntent();

        String numberofuser = i.getStringExtra("phonenumber");

        try {
            activeUser = mooveme.getUser(new Data("user",new PhoneNumber(Integer.parseInt(numberofuser))));
            Toast.makeText(getApplicationContext(),"Hola " + activeUser.getName(),Toast.LENGTH_SHORT).show();
        } catch (UserDoesntExistException e) {
            DialogException.CreateDialog("User Error","User doesnt exist", getApplicationContext());
        }

        et_time = (EditText) findViewById(R.id.et_user_time);
        ranking = (RecyclerView) findViewById(R.id.ranking);
        btn_rentAsset = (Button) findViewById(R.id.btn_user_rentAsset);
        btn_returnAsset = (Button) findViewById(R.id.btn_user_returnAsset);

        ///metodos
        SpinnerAssets();
        SpinnerZone();
        SpinnerParkings();
        SpinnerParkingsReturn();
        SpinnerRanking();


        btn_rentAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),String.valueOf(mooveme.getAssetParkingRepository().getRepository().size()),Toast.LENGTH_SHORT).show();

                assetTypeActive = (AssetType) sp_assetType.getSelectedItem();
                AssetParkingRent = (AssetParking) sp_assetParking.getSelectedItem();
                zoneRent = (Zone) sp_zone.getSelectedItem();
                SpinnerParkingsReturn();
                //zoneactive = (Zone) sp_zone.getSelectedItem();
                RenAsset();
                //Toast.makeText(getApplicationContext(), zoneactive.getName() + " " + assetTypeActive.getName() + " " + AssetParkingRent.getName() + " " + et_time.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        sp_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zoneactive = (Zone) parent.getSelectedItem();
                SpinnerParkings();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_rankingZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rankingZone = (Zone) parent.getSelectedItem();
                SpinnerRanking();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            Toast.makeText(getApplicationContext(),activeUser.getActualTravel().getAsset().getAssetType().getName().toString(),Toast.LENGTH_SHORT).show();
        } catch (AssetTypeDoesNotExistInSpecifiedZoneException assetTypeDoesNotExistInSpecifiedZoneException) {
            DialogException.CreateDialog("Error","Error to rent, asset type doesnt exist in this zone",this);
        } catch (UserCantStartNewTrip e) {
            DialogException.CreateDialog("User Error", "User is already on a trip", getApplicationContext());
        }
    }

    public void returnAnAsset(){
        try {
            AssetParking assetParking = (AssetParking)sp_assetparkingReturn.getSelectedItem();
            activeUser.returnAsset(assetParking);
        } catch (UserIsNotInATripException e) {
            DialogException.CreateDialog("Error","User is not in a trip",this);
        }
    }

    ///SPINNERS

    private void SpinnerZone() {
        ArrayList<Zone> zoneArrayList = mooveme.getZoneRepository().getRepository();
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zoneArrayList);
        sp_zone.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        zoneactive = (Zone)sp_zone.getSelectedItem();


    }

    private void SpinnerAssets() {

        ArrayList<AssetType> assetTypes= mooveme.getAssetTypeRepository().getRepository();
        AssettypeAdapter adapter = new AssettypeAdapter(getApplicationContext(),assetTypes);
        sp_assetType.setAdapter(adapter);

        assetTypeActive = (AssetType) sp_assetType.getSelectedItem();

    }

    private void SpinnerParkings() {
        //get the asset parkings of the zone that you selected
        ArrayList<AssetParking> assetParkings = new ArrayList<>();
        for(AssetParking assetParking: mooveme.getAssetParkingRepository().getRepository()){
            if(assetParking.getZone().getName().equals(zoneactive.getName())){
                assetParkings.add(assetParking);
            }
        }
        if(assetParkings.size() > 0){
            AssetParkingAdapter adapter = new AssetParkingAdapter(getApplicationContext(),assetParkings);
            sp_assetParking.setAdapter(adapter);

        }else{
            ArrayList<AssetParking> assetTypes= mooveme.getAssetParkingRepository().getRepository();
            AssetParkingAdapter adapter = new AssetParkingAdapter(getApplicationContext(),assetTypes);
            sp_assetParking.setAdapter(adapter);
        }



    }
    private void SpinnerParkingsReturn() {
        if(zoneRent != null){
            ArrayList<AssetParking> assetParkings = new ArrayList<>();
            for(AssetParking assetParking:mooveme.getAssetParkingRepository().getRepository()){
                if(assetParking.getZone().getName().equals(zoneRent.getName())){
                    assetParkings.add(assetParking);
                }
            }

            AssetParkingAdapter adapter = new AssetParkingAdapter(getApplicationContext(),assetParkings);
            sp_assetparkingReturn.setAdapter(adapter);
        }



       // AssetParkingReturn = (AssetParking) sp_assetparkingReturn.getSelectedItem();

    }
    private void SpinnerRanking(){
        ArrayList<Zone> zoneArrayList = mooveme.getZoneRepository().getRepository();
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zoneArrayList);
        sp_rankingZone.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rankingZone = (Zone)sp_rankingZone.getSelectedItem();

        ArrayList<RankingInPointTable> rankingTable = zoneactive.getTop10Leaders();

        ranking.setLayoutManager(new LinearLayoutManager(this));

        RankingAdapter rankingAdapter = new RankingAdapter(this, rankingTable);
        this.ranking.setAdapter(rankingAdapter);
    }
}
