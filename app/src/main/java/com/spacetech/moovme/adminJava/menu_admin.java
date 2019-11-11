package com.spacetech.moovme.adminJava;

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

import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Adapters.RankingAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapterParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Fee;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.DialogException;
import com.spacetech.moovme.Exceptions.AdministratorDoesntFoundException;
import com.spacetech.moovme.Exceptions.ElementExistException;
import com.spacetech.moovme.Exceptions.PriceIsAlreadySetException;
import com.spacetech.moovme.Exceptions.UserDoesntExistException;
import com.spacetech.moovme.Exceptions.UserIsAlreadyLockedException;
import com.spacetech.moovme.Exceptions.ZoneAlreadyExistsException;
import com.spacetech.moovme.Exceptions.ZoneDoesNotExistException;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.Points.RankingInPointTable;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;

public class menu_admin extends AppCompatActivity {

    Administrator ActiveAdmin;
    EditText et_name,et_phoneuser,et_zonename,et_zonepoint, et_assettypename, et_assettypepoint,et_aBatchprice,et_aBatchcant,et_aBatchcode,et_parkingName;
    String name,UserPhone,assetTypename;
    Button btn_addadmin,btn_blockuser,btn_addzone,btn_deletezone, btn_addassettype,btn_assetbatch,btn_addAssetParking, btn_updateRanking;
    Spinner sp_assettype,sp_zone,sp_zoneParking, sp_rankingZone;
    AssetType assetTypeActive;
    Zone zoneactive,zoneactiveParking, rankingZone;
    Mooveme mooveme;
    Repository<Administrator> repositoryAdmin;
    private RecyclerView ranking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        mooveme = Persistence.loadMoovme(getApplicationContext());

        Intent i = getIntent();

        try {
            ActiveAdmin = mooveme.findAdmin((String)i.getStringExtra("name"));
            Toast.makeText(this,"Hola " + ActiveAdmin.getName(),Toast.LENGTH_SHORT).show();
        } catch (AdministratorDoesntFoundException administratorDoesntFoundException) {
            administratorDoesntFoundException.printStackTrace();
        }



        sp_assettype = (Spinner) findViewById(R.id.spn_assettype);
        sp_zone = (Spinner) findViewById(R.id.spn_zone);
        sp_zoneParking = (Spinner) findViewById(R.id.sp_zone_parking);

        et_phoneuser = (EditText)findViewById(R.id.et_phoneuser_id);
        et_name = (EditText) findViewById(R.id.et_name_id);
        et_zonename = (EditText) findViewById(R.id.et_zonename_id);
        et_zonepoint = (EditText)findViewById(R.id.et_zonepoints_id);
        et_assettypename = (EditText)findViewById(R.id.et_assettype_id);
        et_assettypepoint = (EditText)findViewById(R.id.et_assettypepoint_id);
        et_aBatchcant = (EditText)findViewById(R.id.et_assetbatch_cant);
        et_aBatchprice = (EditText)findViewById(R.id.et_assetbatch_price);
        et_aBatchcode = (EditText)findViewById(R.id.et_assetbatch_code);
        et_parkingName = (EditText) findViewById(R.id.et_parking_name);

        btn_addadmin = (Button) findViewById(R.id.btn_addadmin_id);
        btn_blockuser = (Button) findViewById(R.id.btn_blocuser_id);
        btn_addzone = (Button) findViewById(R.id.btn_addzone_id);
        btn_deletezone = (Button) findViewById(R.id.btn_deletezone_id);
        btn_addassettype = (Button) findViewById(R.id.btn_addassettype_id);
        btn_assetbatch = (Button)findViewById(R.id.btn_addassetbatch_id);
        btn_addAssetParking = (Button) findViewById(R.id.btn_admin_addparking);

        repositoryAdmin =mooveme.getAdministratorRepository();
        //Toast.makeText(getApplicationContext(), phoneActiveAdmin,Toast.LENGTH_LONG).show();


        btn_addadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_name.getText().toString().isEmpty()){
                    try {
                        addnewAdmin(et_name,ActiveAdmin);
                        Persistence.saveInformation(getApplicationContext(),mooveme);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Complete the field text",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Complete the field text",Toast.LENGTH_SHORT).show();

                }

            }
        });

        btn_blockuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    adminBlockUser(et_phoneuser,ActiveAdmin);
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(),"Complete the field text",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_addzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    adminAddZone(et_zonename,ActiveAdmin);
                    Toast.makeText(getApplicationContext(), "Zone succesfully created", Toast.LENGTH_SHORT).show();

                    Persistence.saveInformation(getApplicationContext(),mooveme);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Error input points",Toast.LENGTH_LONG).show();
                }
                SpinnerZoneType();
                SpinnerZone_to_ParkingType();
            }
        });

        btn_deletezone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDeleteZone(et_zonename,ActiveAdmin);
                Persistence.saveInformation(getApplicationContext(),mooveme);
                SpinnerZoneType();
                SpinnerZone_to_ParkingType();
            }
        });

        btn_addassettype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminCreateAssetType(et_assettypename,et_assettypepoint,ActiveAdmin);
            }
        });

        btn_assetbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addAssetBatch(et_aBatchcant,et_aBatchprice, ActiveAdmin);
                } catch (PriceIsAlreadySetException priceIsAlreadySetException) {
                    Toast.makeText(getApplicationContext(),"Ya esta registrado ese precio",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_addAssetParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAssetParking();
            }
        });
        SpinnerAssetType();
        SpinnerZoneType();
        SpinnerZone_to_ParkingType();

        //Ranking
        ranking = (RecyclerView) findViewById(R.id.ranking);
        sp_rankingZone = (Spinner) findViewById(R.id.sp_user_ranking_zone);
        btn_updateRanking = (Button) findViewById(R.id.btn_updateranking) ;

        SpinnerRanking();

        btn_updateRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ranking();
            }
        });
    }

    private void CreateAssetParking() {

        try {
            String parkingName = et_parkingName.getText().toString().trim();
            zoneactiveParking = (Zone) sp_zoneParking.getSelectedItem();
            ActiveAdmin.addNewAssetParking(zoneactiveParking,parkingName,mooveme,getApplicationContext());
            Persistence.saveInformation(getApplicationContext(),mooveme);
        } catch (ElementExistException e) {
            DialogException.CreateDialog("Error","Error to create a asset parking",this);
        }
    }
    private void SpinnerZone_to_ParkingType() {
        ArrayList<Zone> zones = mooveme.getZoneRepository().getRepository();
        ZoneAdapterParking adapter = new ZoneAdapterParking(getApplicationContext(),zones);
        sp_zoneParking.setAdapter(adapter);

    }

    private void SpinnerZoneType() {
        ArrayList<Zone> zones = mooveme.getZoneRepository().getRepository();
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zones);
        sp_zone.setAdapter(adapter);

    }
    private void addAssetBatch(EditText et_aBatchcant, EditText et_aBatchprice, Administrator activeAdmin) throws PriceIsAlreadySetException {
        int cantidad = Integer.parseInt(et_aBatchcant.getText().toString());
        int price = Integer.parseInt(et_aBatchprice.getText().toString());
        int code = mooveme.getListAssetBachCodes();
        zoneactive = (Zone) sp_zone.getSelectedItem();
        assetTypeActive = (AssetType) sp_assettype.getSelectedItem();
        activeAdmin.buyBatch(assetTypeActive,cantidad,zoneactive,code,new Fee(price));

        //TODO handle exeption with toast
        Toast.makeText(getApplicationContext(),zoneactive.getName(),Toast.LENGTH_SHORT).show();
       // Toast.makeText(getApplicationContext(),"u've buyed " + cantidad + " " + assetTypeActive.getName(), Toast.LENGTH_SHORT).show();
        saveInformation();

    }
    public void SpinnerAssetType(){
        ArrayList<AssetType> assetTypes= mooveme.getAssetTypeRepository().getRepository();
        AssettypeAdapter adapter = new AssettypeAdapter(getApplicationContext(),assetTypes);
        sp_assettype.setAdapter(adapter);
        assetTypename = sp_assettype.getSelectedItem().toString();


    }
    public void adminBlockUser(EditText et_phonenumber, Administrator administrator) {
        UserPhone = et_phonenumber.getText().toString().trim();
        Data dataOfUser = new Data(null,new PhoneNumber(Integer.parseInt(UserPhone)));
        try {
            User userThatWannaBlock = mooveme.findUser(dataOfUser);
            administrator.setUserLock(userThatWannaBlock);

        } catch (UserDoesntExistException | UserIsAlreadyLockedException e) {
            e.printStackTrace();
        }
    }

    public void addnewAdmin(EditText et_name, Administrator administrator){
        try {
            name = et_name.getText().toString();
            Data adminData = new Data(name);
            administrator.registerAdmin(repositoryAdmin,adminData);
        } catch (ElementExistException elementExistException) {
            elementExistException.printStackTrace();
        }finally {
            Toast.makeText(getApplicationContext(), name + " had been added",Toast.LENGTH_SHORT).show();

        }
    }

    public void adminCreateAssetType(EditText et_assetname, EditText et_assetpoint,Administrator administrator){
        String assetName = et_assetname.getText().toString();
        int points = Integer.parseInt(et_assetpoint.getText().toString());
        administrator.createAssetType(assetName,points,mooveme.getAssetTypeRepository());
        saveInformation();
        SpinnerAssetType();
        Toast.makeText(getApplicationContext(),"Se creo el asset con nombre " + assetName , Toast.LENGTH_SHORT).show();
    }
    public void adminAddZone(EditText et_zone,Administrator administrator) {
        try {
            String zonename = et_zone.getText().toString();
            administrator.createNewZone(mooveme.getZoneRepository(),zonename);
            saveInformation();
            mooveme = Persistence.loadMoovme(getApplicationContext());
        } catch (ZoneAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
    public void adminDeleteZone(EditText et_zonename, Administrator administrator) {
        try {
            String zonename = et_zonename.getText().toString();
            administrator.deleteZone(mooveme.getZoneRepository(),zonename);
            saveInformation();
        } catch (ZoneDoesNotExistException e) {
            e.printStackTrace();
        }

    }
    public void saveInformation(){
        Persistence.saveInformation(getApplicationContext(),mooveme);
    }


    //Ranking


    private void SpinnerRanking(){
        ArrayList<Zone> zoneArrayList = mooveme.getZoneRepository().getRepository();
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zoneArrayList);
        sp_rankingZone.setAdapter(adapter);

    }

    private  void Ranking(){
        rankingZone = (Zone)sp_rankingZone.getSelectedItem();

        ArrayList<RankingInPointTable> rankingTable = rankingZone.getTop10Leaders();
        ranking.setLayoutManager(new LinearLayoutManager(this));
        RankingAdapter rankingAdapter = new RankingAdapter(this, rankingTable);
        ranking.setAdapter(rankingAdapter);
    }

}
