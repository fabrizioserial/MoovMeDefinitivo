package com.spacetech.moovme.userJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.DialogException;
import com.spacetech.moovme.Exceptions.UserDoesntExistException;
import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.Persistence;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Users.Data;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

public class Login extends AppCompatActivity {

    private Button btn_login;
    private EditText et_phone;
    String phonenumber;
    User thisUser;
    Mooveme mooveme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layouts);
        mooveme = Persistence.loadMoovme(getApplicationContext());

        btn_login=(Button)findViewById(R.id.btn_login_id);
        et_phone=(EditText)findViewById(R.id.et_phone_id);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    phonenumber = et_phone.getText().toString().trim();
                    User user = new User(new Data("user", new PhoneNumber(Integer.parseInt(phonenumber))));
                    mooveme.loginUser(user);
                    Intent i = new Intent(Login.this,menu_user.class);
                    i.putExtra("phonenumber",phonenumber);
                    startActivity(i);
                } catch (UserDoesntExistException e) {
                    DialogException.CreateDialog("Error user login","User doesnt exist",Login.this);
                }
                /*
                Intent i = new Intent(getApplicationContext(),HomeMenu.class);
                startActivity(i);
                 */
            }
        });
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
