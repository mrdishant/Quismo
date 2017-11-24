package com.nearur.quismo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HelpUser extends AppCompatActivity {

    RadarChart mChart;
    ArrayList<String>quotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_user);
        mChart=(RadarChart)findViewById(R.id.chart4);

        sweetdialog();

        TextView tv = (TextView) findViewById(R.id.textviewdd);
        tv.setTextColor(Color.WHITE);
        tv.setText("Smoking Causes Death");
        tv.setBackgroundColor(Color.rgb(60, 65, 82));


        mChart.setBackgroundColor(Color.rgb(60, 65, 82));

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"Lung Cancer", "Heart Disease", "Stroke", "Aasthma", "Blindness"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        float[] lyear={47,38.5f,46.5f,44.5f,33.5f};
        float[] tyear={58.5f,46.5f,58.5f,49.5f,53.5f};

        for(int i=0;i<5;i++){
            entries1.add(new RadarEntry(lyear[i]));
            entries2.add(new RadarEntry(tyear[i]));
        }


        RadarDataSet set1 = new RadarDataSet(entries1, "Last Year");
        set1.setColor(Color.RED);
        set1.setFillColor(Color.RED);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "This Year");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);

        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.animateX(1000);
        mChart.getYAxis().setEnabled(false);
        mChart.invalidate();

    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public void sweetdialog(){
        quotes=new ArrayList<>();
        quotes.add("Smoking is a habit that drains your money and kills you slowly, one puff after another. Quit smoking, start living.");
        quotes.add("Replacing the smoke on your face with a smile today will replace illness in your life with happiness tomorrow. Quit now.");
        quotes.add("Looking for motivation to quit smoking? Just look into the eyes of your loved ones and ask yourself if they deserve to die because of your second hand smoke.");
        quotes.add(" If you don’t quit smoking, you risk disease and death. But if you do, you will get happiness and good health.");
        quotes.add("All the suffering, stress and addiction comes from not realizing you already are what you are looking for.");
        quotes.add("It is in your moments of decision that your destiny is shaped.");
        quotes.add("The secret of getting ahead is getting started.");
        quotes.add("People often say that motivation doesn’t last. Well, neither does bathing – that’s why we recommend it daily.");
        quotes.add("The surest way not to fail is to determine to succeed.");
        quotes.add("Believe you can and you’re halfway there.");
        quotes.add("Our strength grows out of our weakness.");
        quotes.add("At least three times every day take a moment and ask yourself what is really important. Have the wisdom and the courage to build your life around your answer.");
        quotes.add("All behavioral or mood disorders – including depression, OCD, ADHD and addiction – have some neurochemical components, but sufferers can still work to overcome them.");
        quotes.add("Checking your ego, abandoning it, letting it go, is a huge part of recovery from addiction.");

        Collections.shuffle(quotes);

        new SweetAlertDialog(HelpUser.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Motivation!")
                .setContentText(quotes.get(0).toString())
                .setCustomImage(R.drawable.tip)
                .show();
    }
}
