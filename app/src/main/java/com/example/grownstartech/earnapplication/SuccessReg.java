package com.example.grownstartech.earnapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessReg extends AppCompatActivity {
    Button proceedSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_reg);

        proceedSignInBtn = (Button) findViewById(R.id.btn_sign_in_proceed_reg);
        proceedSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SuccessReg.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
