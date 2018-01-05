package com.itechbd.locationdemo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class First_Fragment extends Fragment {

    ListView listView;

    public First_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_first_, container, false);


        listView = (ListView) viewGroup.findViewById(R.id.listview1);

        callApiForData();

        return viewGroup;

    }


    public class ItemAdapter extends ArrayAdapter {

        List<ImageModel.DataBean> itemList;
        int resourceId;
        LayoutInflater layoutInflater;

        public ItemAdapter(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, resource, objects);
            this.itemList = objects;
            this.resourceId = resource;
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(resourceId, null, false);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

            final String a = itemList.get(position).getImage();

            byte[] decodedString = Base64.decode(a, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            imageView.setImageBitmap(decodedByte);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Second_Fragment second_fragment = new Second_Fragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("imageString", a);
                    Log.d("imageString", a);
                    second_fragment.setArguments(bundle);

//                    FragmentManager fm = getFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    second_fragment.setArguments(bundle);
//                    ft.add(R.id.second_layout, second_fragment);
//                    ft.commit();


                }
            });

            return convertView;
        }


        @Override
        public int getCount() {
            return itemList.size();
        }

        @Nullable
        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

    }

    public void callApiForData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response er vetore sob Json data ache...etake purse korbo

                        Gson gson = new Gson();
                        ImageModel imageModel = gson.fromJson(response, ImageModel.class);

                        ItemAdapter itemAdapter = new ItemAdapter(getContext(), R.layout.item_listview1, imageModel.getData());

                        listView.setAdapter(itemAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
