package com.spacetech.moovme.userJava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spacetech.moovme.Mooveme;
import com.spacetech.moovme.R;
import com.spacetech.moovme.Users.PhoneNumber;
import com.spacetech.moovme.Users.User;

public class Login extends AppCompatActivity {

    private Button btn_login;
    private EditText et_phone;
    String phonenumber;
    User thisUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layouts);

        btn_login=(Button)findViewById(R.id.btn_login_id);
        et_phone=(EditText)findViewById(R.id.et_phone_id);

        phonenumber = et_phone.getText().toString();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mooveme.login(new PhoneNumber(phonenumber));
                Toast.makeText(getApplicationContext(),"LOGGIN SUCCESS",Toast.LENGTH_LONG).show();
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
