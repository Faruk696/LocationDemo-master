package com.itechbd.locationdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        First_Fragment first_fragment = new First_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.first_layout,first_fragment,first_fragment.getTag())
                .commit();

        Second_Fragment second_fragment = new Second_Fragment();

        manager.beginTransaction().replace(R.id.second_layout,second_fragment,second_fragment.getTag())
                .commit();


    }
}
