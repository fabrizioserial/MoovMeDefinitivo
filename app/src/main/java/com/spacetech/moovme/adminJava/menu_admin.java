package com.spacetech.moovme.adminJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.Adapters.AssettypeAdapter;
import com.spacetech.moovme.Adapters.ZoneAdapter;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Zone;
import com.spacetech.moovme.Exeptions.ElementExistExeption;
import com.spacetech.moovme.Exeptions.UserDoesntExistException;
import com.spacetech.moovme.Exeptions.UserIsAlreadyLockedExeption;
import com.spacetech.moovme.Exeptions.ZoneAlreadyExistsException;
import com.spacetech.moovme.Exeptions.ZoneDoesNotExistException;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Repository.ListAssetBachCodes;
import com.spacetech.moovme.Repository.Repository;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

import java.util.ArrayList;

public class menu_admin extends AppCompatActivity {

    Administrator ActiveAdmin;
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

        Intent i = getIntent();
        ActiveAdmin = (Administrator)i.getSerializableExtra("name");
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
                    adminAddZone(et_zonename,et_zonepoint,ActiveAdmin);
                    Persistence.saveInformation(getApplicationContext(),mooveme);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Error input points",Toast.LENGTH_LONG).show();
                }
                SpinnerZoneType();
            }
        });

        btn_deletezone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDeleteZone(et_zonename,ActiveAdmin);
                Persistence.saveInformation(getApplicationContext(),mooveme);
                SpinnerZoneType();
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
                addAssetBatch(assetTypename,et_aBatchcant,et_aBatchprice,et_aBatchcode, ActiveAdmin);
            }
        });
        SpinnerAssetType();
        SpinnerZoneType();
    }
    private void SpinnerZoneType() {
        ArrayList zones = mooveme.getZoneRepository().getRepository();
        ZoneAdapter adapter = new ZoneAdapter(getApplicationContext(),zones);
        sp_zone.setAdapter(adapter);
        zoneactive = (Zone) sp_zone.getSelectedItem();
    }
    private void addAssetBatch(String assetTypename, EditText et_aBatchcant, EditText et_aBatchprice, EditText et_aBatchcode, Administrator activeAdmin) {
        int cantidad = Integer.parseInt(et_aBatchcant.getText().toString());
        int price = Integer.parseInt(et_aBatchprice.getText().toString());
        Integer codeint = Integer.parseInt(et_aBatchcode.getText().toString());
        activeAdmin.buyBatch(assetTypeActive,cantidad,zoneactive,new ListAssetBachCodes(),price);
        Toast.makeText(getApplicationContext(),"u've buyed " + cantidad + " " + assetTypeActive.getName(), Toast.LENGTH_SHORT).show();

    }
    public void SpinnerAssetType(){
        ArrayList assetTypes= mooveme.getAssetRepository().getRepository();
        AssettypeAdapter adapter = new AssettypeAdapter(getApplicationContext(),assetTypes);
        sp_assettype.setAdapter(adapter);
        assetTypename = sp_assettype.getSelectedItem().toString();
        assetTypeActive = (AssetType) sp_assettype.getSelectedItem();

    }
    public void adminBlockUser(EditText et_phonenumber, Administrator administrator) {
        UserPhone = et_phonenumber.getText().toString();
        Data dataOfUser = new Data(null,new PhoneNumber(Integer.parseInt(UserPhone)));
        try {
            User userThatWannaBlock = mooveme.findUser(dataOfUser);
            administrator.setUserLock(userThatWannaBlock,true);

        } catch (UserDoesntExistException | UserIsAlreadyLockedExeption e) {
            e.printStackTrace();
        }
    }

    public void addnewAdmin(EditText et_name, Administrator administrator){
        try {
            name = et_name.getText().toString();
            Data adminData = new Data(name);
            administrator.registerAdmin(repositoryAdmin,adminData);
        } catch (ElementExistExeption elementExistExeption) {
            elementExistExeption.printStackTrace();
        }finally {
            Toast.makeText(getApplicationContext(), name + " had been added",Toast.LENGTH_SHORT).show();

        }
    }

    public void adminCreateAssetType(EditText et_assetname, EditText et_assetpoint,Administrator administrator){
        String assetName = et_assetname.getText().toString();
        int points = Integer.parseInt(et_assetpoint.getText().toString());
        administrator.createAssetType(assetName,points,mooveme.getAssetTypeRepository());
        Toast.makeText(getApplicationContext(),"Se creo el asset con nombre " + assetName , Toast.LENGTH_SHORT).show();
    }
    public void adminAddZone(EditText et_zone,EditText et_point_zone,Administrator administrator) {
        try {
            String zonename = et_zone.getText().toString();
            administrator.createNewZone(mooveme.getZoneRepository(),zonename);
        } catch (ZoneAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
    public void adminDeleteZone(EditText et_zonename, Administrator administrator) {
        try {
            String zonename = et_zonename.getText().toString();
            Zone zone = administrator.getZone(mooveme.getZoneRepository(),et_zonename);
            administrator.deleteZone(mooveme.getZoneRepository(),zone);
        } catch (ZoneDoesNotExistException e) {
            e.printStackTrace();
        }

    }


}
