package com.example.grownstartech.earnapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.grownstartech.earnapplication.Models.Common;

public class HomeActivity extends AppCompatActivity {
    Button keyBtn;
    TextView keyTypeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        keyBtn=(Button) findViewById(R.id.btn_sign_in_proceed_reg);
        keyBtn.setText(Common.user_key.toString());

        keyTypeView =  (TextView) findViewById(R.id.txtVRegCont);
        keyTypeView.setText(Common.tokenType);

    }
}
