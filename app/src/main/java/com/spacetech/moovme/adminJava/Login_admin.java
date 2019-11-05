package com.spacetech.moovme.adminJava;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spacetech.moovme.AdministratorDoesntFoundExeption;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Users.Administrator;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.User;

import java.io.Serializable;
import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;

public class Login_admin extends Fragment {

    private Button btn_login;
    EditText et_name_adm;
    User thisUser;
    Mooveme mooveme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.login_admin_layouts, container, false);
        btn_login=(Button)v.findViewById(R.id.btn_login_id);
        et_name_adm = (EditText)v.findViewById(R.id.et_name_id);
        loadData();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameadmin = et_name_adm.getText().toString();
                Data dataCheck = new Data(nameadmin,null);
                try {
                    Administrator administrator = mooveme.loginAdministrator(new Administrator(dataCheck));
                    Intent i = new Intent(getContext(), menu_admin.class);
                    i.putExtra("name", (Serializable) administrator);
                    startActivity(i);
                    Toast.makeText(getContext(),"Bienvenido",Toast.LENGTH_LONG).show();


                } catch (AdministratorDoesntFoundExeption administratorDoesntFoundExeption) {
                    administratorDoesntFoundExeption.printStackTrace();
                }
                /*
                Intent i = new Intent(getContext(), menu_admin.class);
                i.putExtra("name", "Fabro");
                startActivity(i);
                */
            }
        });

        return v;
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Mooveme",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Mooveme", null);
        Type type = new TypeToken<Mooveme>() {}.getType();
        Mooveme mooveme = gson.fromJson(json, type);
        this.mooveme = mooveme;

    }


}
