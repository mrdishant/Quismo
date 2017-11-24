package com.nearur.quismo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyChart extends AppCompatActivity {

    PieChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_chart);
        mChart =(PieChart)findViewById(R.id.chart);

        List<PieEntry> pieEntryList=new ArrayList();

        int saved=0,wasted=0;

        String[] p={Util.saved,Util.wasted};
        Cursor c1=getContentResolver().query(Util.u,p,null,null,null);
        if(c1!=null && c1.getCount()>0){
            while (c1.moveToNext()){
                saved=saved+c1.getInt(0);
                wasted=wasted+c1.getInt(1);
            }
        }

        pieEntryList.add(new PieEntry(saved,"Money Saved"));
        pieEntryList.add(new PieEntry(wasted,"Money Spent"));

        PieDataSet dataSet=new PieDataSet(pieEntryList,"Consumption Analysis");
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        Collections.shuffle(colors);
        dataSet.setColors(colors);


        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.setData(new PieData(dataSet));
        mChart.animateY(1000);
        mChart.invalidate();

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Intent i=new Intent(MoneyChart.this,BarChart.class);
                i.putExtra("What",((PieEntry)e).getLabel());
                startActivity(i);
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Money Analysis\nOn Cigarettes Smoking");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 21, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 21, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 21, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 21, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 21, s.length(), 0);
        return s;
    }

}
