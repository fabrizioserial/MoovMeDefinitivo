package com.spacetech.moovme.SlidePage;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.spacetech.moovme.Fragment.midFragment;
import com.spacetech.moovme.adminJava.Login_admin;
import com.spacetech.moovme.R;
import com.spacetech.moovme.userJava.RegisterUser;

import java.util.ArrayList;
import java.util.List;


public class Menu_activity extends AppCompatActivity {


    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mainactivity);

        //
        List<Fragment> list = new ArrayList<>();
        list.add(new Login_admin());
        list.add(new midFragment());
        list.add(new RegisterUser());

        pager = findViewById(R.id.viewer);
        pagerAdapter = new SlidePager(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(1);
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
