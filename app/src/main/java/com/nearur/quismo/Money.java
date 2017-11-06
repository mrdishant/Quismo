package com.nearur.quismo;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Money extends Fragment {

    TextView textView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_money, container, false);
        textView=(TextView)view.findViewById(R.id.moneysaved);
        sharedPreferences=getActivity().getSharedPreferences("Quismo", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        if(sharedPreferences.contains("Daily")){
            textView.setText("Money Saved\n Money:"+sharedPreferences.getInt("Daily",0)*sharedPreferences.getInt("Days",1)+"\n");
        }else{
            textView.setText("Click on Me to Setup");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showdialog();
                }
            });
        }

        return view;
    }

    void showdialog(){
        final Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(false);
        dialog.show();
        final EditText price=dialog.findViewById(R.id.editTextprice);
        final EditText number=dialog.findViewById(R.id.editTextnumber);
        Button btn=dialog.findViewById(R.id.buttondone);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(price.length()>0&&number.length()>0){
                   int priceone=Integer.parseInt(price.getText().toString().trim())/12;
                   int numberdaily=Integer.parseInt(number.getText().toString().trim());
                   editor.putInt("Daily",priceone*numberdaily);
                   editor.commit();
                   dialog.dismiss();
               }else{
                   Toast.makeText(getContext(),"Please Enter all Details",Toast.LENGTH_LONG).show();
               }
            }
        });

    }

}
