package com.spacetech.moovme.Fragment;

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

public class midFragment extends Fragment {

    EditText et_name,et_password,et_phonenumber;
    String name;
    String phonenumber;
    Button btn_register;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.midfragment_layout, container, false);
        return v;
    }
}
