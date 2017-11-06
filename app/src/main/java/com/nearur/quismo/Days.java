package com.nearur.quismo;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Days extends Fragment {



    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_days, container, false);

        sharedPreferences=getContext().getSharedPreferences("Quismo", Context.MODE_PRIVATE);
        TextView textView=(TextView)view.findViewById(R.id.textdays);
        textView.setText("Days without Smoke :"+sharedPreferences.getInt("Dayssmoke",1));
        return view;
    }

}
