package com.nearur.quismo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaysChart extends AppCompatActivity {

    LineChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_days_chart);

        chart=(LineChart)findViewById(R.id.chart3);

        ArrayList<Entry> entries=new ArrayList<>();

        String[] p={Util.number};
        Cursor c=getContentResolver().query(Util.u,p,null,null,null,null);
        if(c!=null&&c.getCount()>0){
            float x=0;
            while (c.moveToNext()){
                entries.add(new Entry(x,c.getInt(0)));
                x=x+0.5f;
            }
        }

        if(entries.size()>0){
        LineDataSet dataSet=new LineDataSet(entries,"Streak");
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c1 : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c1);

        for (int c1 : ColorTemplate.JOYFUL_COLORS)
            colors.add(c1);

        for (int c1 : ColorTemplate.COLORFUL_COLORS)
            colors.add(c1);

        for (int c1 : ColorTemplate.LIBERTY_COLORS)
            colors.add(c1);

        for (int c1 : ColorTemplate.PASTEL_COLORS)
            colors.add(c1);

        colors.add(ColorTemplate.getHoloBlue());
        Collections.shuffle(colors);
        dataSet.setColors(colors);
        chart.setData(new LineData(dataSet));
        }

        chart.animateY(1000);
        chart.invalidate();


    }
}
