package com.spacetech.moovme.adminJava;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class menu_admin extends AppCompatActivity {

    String ActiveAdmin;
    EditText et_name,et_phoneuser,et_zonename,et_zonepoint, et_assettypename, et_assettypepoint,et_aBatchprice,et_aBatchcant,et_aBatchcode;
    String name,UserPhone,assetTypename;
    Button btn_addadmin,btn_blockuser,btn_addzone,btn_deletezone, btn_addassettype,btn_assetbatch;
    Spinner sp_assettype,sp_zone;
    AssetType assetTypeActive;
    Zone zoneactive;
    Mooveme mooveme;
    Repository<Administrator> repositoryAdmin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        mooveme = Persistence.loadMoovme(getApplicationContext());

        Intent i = new Intent();
        i = getIntent();
        ActiveAdmin = i.getStringExtra("name");
        sp_assettype = (Spinner) findViewById(R.id.spn_assettype);
        sp_zone = (Spinner) findViewById(R.id.spn_zone);

        et_phoneuser = (EditText)findViewById(R.id.et_phoneuser_id);
        et_name = (EditText) findViewById(R.id.et_name_id);
        et_zonename = (EditText) findViewById(R.id.et_zonename_id);
        et_zonepoint = (EditText)findViewById(R.id.et_zonepoints_id);
        et_assettypename = (EditText)findViewById(R.id.et_assettype_id);
        et_assettypepoint = (EditText)findViewById(R.id.et_assettypepoint_id);
        et_aBatchcant = (EditText)findViewById(R.id.et_assetbatch_cant);
        et_aBatchprice = (EditText)findViewById(R.id.et_assetbatch_price);
        et_aBatchcode = (EditText)findViewById(R.id.et_assetbatch_code);

        btn_addadmin = (Button) findViewById(R.id.btn_addadmin_id);
        btn_blockuser = (Button) findViewById(R.id.btn_blocuser_id);
        btn_addzone = (Button) findViewById(R.id.btn_addzone_id);
        btn_deletezone = (Button) findViewById(R.id.btn_deletezone_id);
        btn_addassettype = (Button) findViewById(R.id.btn_addassettype_id);
        btn_assetbatch = (Button)findViewById(R.id.btn_addassetbatch_id);

        repositoryAdmin =();
        //Toast.makeText(getApplicationContext(), phoneActiveAdmin,Toast.LENGTH_LONG).show();
        final Administrator activeAdmin = repositoryAdmin.findAdmin(ActiveAdmin);

        btn_addadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_name.getText().toString().isEmpty()){
                    try {
                        addnewAdmin(et_name,activeAdmin);
                        saveInformation("Admins",Mooveme.getRepositoryZone());
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
                    adminblockuser(et_phoneuser,activeAdmin);
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(),"Complete the field text",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_addzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    adminaddZone(et_zonename,et_zonepoint,activeAdmin);
                    saveInformation("Zone",Mooveme.getRepositoryZone());
                } catch (Exeptions.ZoneAlreadyExistsException e) {
                    Toast.makeText(getApplicationContext(),"This zone already exist",Toast.LENGTH_LONG).show();
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Error input points",Toast.LENGTH_LONG).show();
                }
                SpinnerZoneType();
            }
        });

        btn_deletezone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    admindeletezone(et_zonename,activeAdmin);
                } catch (Exeptions.ZoneDoesNotExistException e) {
                    Toast.makeText(getApplicationContext(),"This zone doesnt exist", Toast.LENGTH_LONG).show();
                }

                saveInformation("Zone",Mooveme.getRepositoryZone());
                SpinnerZoneType();
            }
        });

        btn_addassettype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminCreateAssetType(et_assettypename,et_assettypepoint,activeAdmin);
            }
        });

        btn_assetbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAssetBatch(assetTypename,et_aBatchcant,et_aBatchprice,et_aBatchcode,activeAdmin);
            }
        });
        SpinnerAssetType();
        SpinnerZoneType();
    }
    private void SpinnerZoneType() {
        HashMap zoneHashMap = Mooveme.getRepositoryZone().getCollection();
        Collection values = zoneHashMap.values();
        ArrayList<Zone> zoneArrayList = new ArrayList<Zone>(values);
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zoneArrayList);
        sp_zone.setAdapter(adapter);
        zoneactive = (Zone) sp_zone.getSelectedItem();
    }
    private void addAssetBatch(String assetTypename, EditText et_aBatchcant, EditText et_aBatchprice, EditText et_aBatchcode, Administrator activeAdmin) {
        int cantidad = Integer.parseInt(et_aBatchcant.getText().toString());
        int price = Integer.parseInt(et_aBatchprice.getText().toString());
        Integer codeint = Integer.parseInt(et_aBatchcode.getText().toString());
        activeAdmin.buyBatch(assetTypeActive,cantidad,zoneactive,new Repository.ListAssetBachCodes(),price);
        Toast.makeText(getApplicationContext(),"u've buyed " + cantidad + " " + assetTypeActive.getName(), Toast.LENGTH_SHORT).show();

    }
    public void SpinnerAssetType(){
        ArrayList assetTypes= Mooveme.getRepositoryAsset().getCollection();
        AssettypeAdapter adapter = new AssettypeAdapter(getApplicationContext(),assetTypes);
        sp_assettype.setAdapter(adapter);
        assetTypename = sp_assettype.getSelectedItem().toString();
        assetTypeActive = (Assets.AssetType) sp_assettype.getSelectedItem();

    }
    public void adminblockuser(EditText et_phonenumber, Administrator administrator) throws NullPointerException {
        UserPhone = et_phoneuser.getText().toString();
        administrator.setUserLock(Mooveme.getRepositoryUser(),new Users.PhoneNumber(UserPhone),true);
    }

    public void addnewAdmin(EditText et_name, Administrator administrator) throws Exception{
        name = et_name.getText().toString();
        administrator.registerAdmin(repositoryAdmin,name);
        Toast.makeText(getApplicationContext(), name + " had been added",Toast.LENGTH_SHORT).show();
    }

    public void adminCreateAssetType(EditText et_assetname, EditText et_assetpoint,Administrator administrator){
        String assetName = et_assetname.getText().toString();
        int points = Integer.parseInt(et_assetpoint.getText().toString());
        administrator.createAssetType(assetName,points,Mooveme.getRepositoryAsset());
        Toast.makeText(getApplicationContext(),"Se creo el asset con nombre " + assetName , Toast.LENGTH_SHORT).show();
    }
    public void adminaddZone(EditText et_zone,EditText et_point_zone,Administrator administrator) throws Exeptions.ZoneAlreadyExistsException,NumberFormatException {
        String zonename = et_zone.getText().toString();
        int point = Integer.parseInt(et_point_zone.getText().toString());
        administrator.createNewZone(Mooveme.getRepositoryZone(),point,zonename,getApplicationContext());
    }
    public void admindeletezone(EditText et_zonename, Administrator administrator) throws Exeptions.ZoneDoesNotExistException {
        String zonename = et_zonename.getText().toString();
        administrator.deleteZone(Mooveme.getRepositoryZone(),zonename);
    }


}
