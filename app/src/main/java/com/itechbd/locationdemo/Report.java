package com.itechbd.locationdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Report extends AppCompatActivity implements First_Fragment.OnFragmentInteractionListener {

    private Second_Fragment second_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        First_Fragment first_fragment = new First_Fragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.first_layout,first_fragment,first_fragment.getTag())
                .commit();

        second_fragment = new Second_Fragment();

        manager.beginTransaction().replace(R.id.second_layout,second_fragment,second_fragment.getTag())
                .commit();


    }

    @Override
    public void sendImage(String imageUrl) {
        if (second_fragment!=null){
            second_fragment.onImageClickFromFirstFragment(imageUrl);
        }
    }
}
