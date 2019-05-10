package com.example.clienthttp.treatment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clienthttp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MesurementsFragment extends Fragment {


    public MesurementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mesurements, container, false);
        //Here you can display what you set on the ViewPagerAdapter (declare and use the necessary views)
        TextView text = view.findViewById(R.id.text_on_fragment);
        text.setText(getArguments().getString("text","Something went wrong... :("));
        return view;
    }

}
