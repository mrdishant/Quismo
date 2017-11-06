package com.nearur.quismo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Details extends AppCompatActivity {

    @InjectView(R.id.editTextName)
    EditText name;

    @InjectView(R.id.editTextAge)
    EditText age;
    @InjectView(R.id.radioButtonMale)
    RadioButton male;

    @InjectView(R.id.radioButtonFemale)
    RadioButton female;

    @InjectView(R.id.buttonSubmit)
    Button submit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);

        Intent i=new Intent("a.b.c.d");
        sendBroadcast(i);

        sharedPreferences=getSharedPreferences("Quismo",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.length() > 0 && age.length() > 0 && (male.isChecked() || female.isChecked())) {
                    editor.putString("Name", name.getText().toString().trim());
                    editor.putInt("Age", Integer.parseInt(age.getText().toString().trim()));

                    if (male.isChecked()) {
                        editor.putString("Gender", "Male");
                    } else {
                        editor.putString("Gender", "Female");

                    }
                    editor.putInt("Days",1);
                    editor.putInt("Dayssmoke",1);
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
