package com.spacetech.moovme.adminJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.spacetech.moovme.R;
import com.spacetech.moovme.Users.User;

public class Login_admin extends Fragment {

    private Button btn_login;
    EditText et_name_adm;
    User thisUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.login_admin_layouts, container, false);
        btn_login=(Button)v.findViewById(R.id.btn_login_id);
        et_name_adm = (EditText)v.findViewById(R.id.et_name_id);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String nameadmin = et_name_adm.getText().toString();
                if(Mooveme.loginadmin(nameadmin)){
                    Intent i = new Intent(getContext(), menu_admin.class);
                    i.putExtra("name", nameadmin);
                    startActivity(i);
                    Toast.makeText(getContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),Mooveme.getName(),Toast.LENGTH_LONG).show();
                }
                */
                Intent i = new Intent(getContext(), menu_admin.class);
                i.putExtra("name", "Fabro");
                startActivity(i);
                
            }
        });
        return v;
    }
}
