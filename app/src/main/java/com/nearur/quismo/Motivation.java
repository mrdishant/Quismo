package com.nearur.quismo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class Motivation extends Fragment {

    ArrayList<String> quotes;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_motivation, container, false);
        textView=(TextView)view.findViewById(R.id.motivationquote);
        quotes=new ArrayList<>();
        quotes.add("Smoking is a habit that drains your money and kills you slowly, one puff after another. Quit smoking, start living.");
        quotes.add("Replacing the smoke on your face with a smile today will replace illness in your life with happiness tomorrow. Quit now.");
        quotes.add("Looking for motivation to quit smoking? Just look into the eyes of your loved ones and ask yourself if they deserve to die because of your second hand smoke.");
        quotes.add(" If you don’t quit smoking, you risk disease and death. But if you do, you will get happiness and good health.");
        Collections.shuffle(quotes);
        textView.setText(quotes.get(0));
        return view;
    }

}
