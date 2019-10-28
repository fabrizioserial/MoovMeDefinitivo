package com.spacetech.moovme.userJava;

import android.content.Intent;
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

import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

public class RegisterUser extends Fragment {

    private EditText et_name,et_password,et_phonenumber;
    private String name;
    private String phonenumber;
    private Button btn_register,btn_login;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.register_layouts, container, false);
        //View v = super.onCreateView(inflater, container, savedInstanceState);

        et_name = (EditText) v.findViewById(R.id.et_name_id);
        et_phonenumber = (EditText) v.findViewById(R.id.et_phone_id);
        btn_register = (Button) v.findViewById(R.id.btn_register_id);
        btn_login = (Button) v.findViewById(R.id.btn_login_id);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_name.getText().toString().trim();
                phonenumber = et_phonenumber.getText().toString().trim();

                if(name.isEmpty() || phonenumber.isEmpty() ){
                    Toast.makeText(getContext(),"Complete the fields", Toast.LENGTH_LONG).show();

                }else {
                    PhoneNumber userPhoneNumber = new PhoneNumber(phonenumber);
                    Mooveme.register(name, userPhoneNumber);
                    User user = Mooveme.getRepositoryUser().findUser(new PhoneNumber(phonenumber));
                    Toast.makeText(getContext(),user.getPhoneNumber().getPhoneNumber(), Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterUser.this.getActivity(), Login.class);
                startActivity(i);
            }
        });


        return v;
    }
}
