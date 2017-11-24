package com.nearur.quismo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Details extends AppCompatActivity {


    EditText name,price,number;
    EditText age;
    RadioButton male;
    RadioButton female;

    Button submit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        name=(EditText)findViewById(R.id.editTextName);
        age=(EditText)findViewById(R.id.editTextAge);
        female=(RadioButton)findViewById(R.id.radioButtonFemale);
        male=(RadioButton)findViewById(R.id.radioButtonMale);
        submit=(Button)findViewById(R.id.buttonSubmit);
        price=(EditText)findViewById(R.id.editTextprice);
        number=(EditText)findViewById(R.id.editTextnumber);



        Intent i=new Intent("quismoeight");
        sendBroadcast(i);

        sharedPreferences=getSharedPreferences("Quismo",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.length() > 0 && age.length() > 0 && (male.isChecked() || female.isChecked())&&price.length()>0&&number.length()>0){
                    editor.putString("Name", name.getText().toString().trim());
                    editor.putInt("Age", Integer.parseInt(age.getText().toString().trim()));
                    int priceone=Integer.parseInt(price.getText().toString().trim())/12;
                    int numberdaily=Integer.parseInt(number.getText().toString().trim());
                    editor.putInt("Daily",priceone*numberdaily);
                    editor.putInt("Price",priceone);

                    if (male.isChecked()) {
                        editor.putString("Gender", "Male");
                    } else {
                        editor.putString("Gender", "Female");

                    }

                    editor.putInt("Days",0);
                    editor.putInt("Dayssmoke",0);
                    editor.commit();

                    Intent i=new Intent(Details.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(Details.this,"Please Enter all Details",Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}
