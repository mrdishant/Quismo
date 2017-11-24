package com.nearur.quismo;

import android.database.Cursor;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarChart extends AppCompatActivity {

    com.github.mikephil.charting.charts.BarChart mChart;
    Boolean le=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bar_chart);

        mChart =(com.github.mikephil.charting.charts.BarChart)findViewById(R.id.chart2);

        String label=getIntent().getStringExtra("What");
        ArrayList<String> labels=new ArrayList<>();


        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(60);
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);




        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);




        YAxis leftAxis = mChart.getAxisLeft();

        leftAxis.setLabelCount(8, false);

        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        rightAxis.setLabelCount(8, false);

        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        List<BarEntry>barEntries=new ArrayList<>();

        if(label.equals("Money Saved")){
            String[] p={Util.date,Util.saved};
            Cursor c=getContentResolver().query(Util.u,p,null,null,null);
            float x=0;
            if(c!=null&&c.getCount()>0){
                while(c.moveToNext()){
                    barEntries.add(new BarEntry(x,c.getInt(1)));
                    x=x+0.5f;
                    if(le){
                        labels.add(c.getString(0).substring(0,c.getString(0).indexOf("/")));
                        le=false;
                    }else{
                        le=true;
                    }

                }
            }
        }else{
            String[] p={Util.date,Util.wasted};
            Cursor c=getContentResolver().query(Util.u,p,null,null,null);
            float x=0;
            if(c!=null&&c.getCount()>0){
                while(c.moveToNext()){
                    barEntries.add(new BarEntry(x,c.getInt(1)));
                    x=x+0.5f;
                    if(le){
                        labels.add(c.getString(0).substring(0,c.getString(0).indexOf("/")));
                        le=false;
                    }else{
                        le=true;
                    }
                }
            }
        }

        BarDataSet dataSet=new BarDataSet(barEntries,label);
        ArrayList<Integer> colors = new ArrayList<Integer>();

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
        mChart.setData(new BarData(dataSet));
        mChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        mChart.animateX(1000);
        mChart.invalidate();

        final RectF mOnValueSelectedRectF=new RectF();

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;

                RectF bounds = mOnValueSelectedRectF;
                mChart.getBarBounds((BarEntry) e, bounds);
                MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);
                Log.i("bounds", bounds.toString());
                Log.i("position", position.toString());

                Log.i("x-index",
                        "low: " + mChart.getLowestVisibleX() + ", high: "
                                + mChart.getHighestVisibleX());

                MPPointF.recycleInstance(position);
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }
}
