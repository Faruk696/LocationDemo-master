package com.itechbd.locationdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_Fragment extends Fragment {


    public Second_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_second_, container, false);

        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.imageView2);


//        String imageString = getArguments().getString("imageString");

//        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//        imageView.setImageBitmap(decodedByte);


        return viewGroup;
    }

}
