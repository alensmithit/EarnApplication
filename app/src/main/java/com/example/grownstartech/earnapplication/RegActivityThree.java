package com.example.grownstartech.earnapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class RegActivityThree extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reg_three );

        Spinner genderspinner = (Spinner) findViewById( R.id.reg_gender_spinner );
        //Spiner adapter and spinner layout
        ArrayAdapter<CharSequence> regspineradapter = ArrayAdapter.createFromResource( this, R.array.signup_reg3_gender,
                R.layout.gender_spinner_list);
        //Specify the layout to use when the list of choice appears
        regspineradapter.setDropDownViewResource( R.layout.gender_dropdown_layout);
        //Apply the adapter to the spinner
        genderspinner.setAdapter( regspineradapter );
        genderspinner.setOnItemSelectedListener( this );
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition( i ).toString();
        Toast.makeText( adapterView.getContext(), text, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
