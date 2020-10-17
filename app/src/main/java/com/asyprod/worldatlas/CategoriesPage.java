package com.asyprod.worldatlas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class CategoriesPage extends AppCompatActivity {

    //Creating All Views
    CardView asia,europe,america,africa,oceania,polar,seeAll;
    ImageView asiaImg,europeImg,africaImg,americaImg,oceaniaImg,polarImg,seeAllImg;

    LineChart popChart;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        //Hiding the support action bar
        getSupportActionBar().hide();

        //Binding all the created views with that in xml file
        asia = findViewById(R.id.asia);
        europe = findViewById(R.id.europe);
        america = findViewById(R.id.america);
        africa = findViewById(R.id.africa);
        oceania = findViewById(R.id.oceania);
        polar = findViewById(R.id.polar);
        seeAll = findViewById(R.id.seeAll);

        asiaImg = findViewById(R.id.asiaImg);
        europeImg = findViewById(R.id.europeImg);
        americaImg = findViewById(R.id.americaImg);
        africaImg = findViewById(R.id.africaImg);
        oceaniaImg = findViewById(R.id.oceaniaImg);
        polarImg = findViewById(R.id.polarImg);
        seeAllImg= findViewById(R.id.seeAllImg);


        //Creating population line chart graph
        ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("1990");
        xLabel.add("1991");
        xLabel.add("1992");
        xLabel.add("1993");
        xLabel.add("1994");
        xLabel.add("1995");
        xLabel.add("1996");
        xLabel.add("1997");
        xLabel.add("1998");
        xLabel.add("1999");
        xLabel.add("2000");
        xLabel.add("2001");
        xLabel.add("2002");
        xLabel.add("2003");
        xLabel.add("2004");
        xLabel.add("2005");
        xLabel.add("2006");
        xLabel.add("2007");
        xLabel.add("2008");
        xLabel.add("2009");
        xLabel.add("2010");
        xLabel.add("2011");
        xLabel.add("2012");
        xLabel.add("2013");
        xLabel.add("2014");
        xLabel.add("2015");
        xLabel.add("2016");
        xLabel.add("2017");
        xLabel.add("2018");
        xLabel.add("2019");
        xLabel.add("2020");
        popChart = findViewById(R.id.line);
        LineDataSet lineDataSet = new LineDataSet(popChartValues(),"World Population(CRORES) 1990-2020");
        lineDataSet.setDrawFilled(true);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
        lineDataSet.setFillDrawable(drawable);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        popChart.setData(data);
        popChart.invalidate();
        popChart.setTouchEnabled(true);
        popChart.getDescription().setEnabled(false);
        CustomMarkerView marker = new CustomMarkerView(this,R.layout.custom_marker_view,xLabel);
        popChart.setMarker(marker);
        popChart.animateX(1800);
        XAxis xAxis = popChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xLabel.get((int)value);
            }
        });

        //For creating pie chart
        pieChart = (PieChart)findViewById(R.id.pieChart);
        PieDataSet pieDataSet = new PieDataSet(getData(),"World Land Distribution(%)");
        final int[] MY_COLORS = {
                Color.rgb(255,0,0),
                Color.rgb(0,255,255),
                Color.rgb(128,255,0),
                Color.rgb(255,0,255),
                Color.rgb(255,128,0),
                Color.rgb(247, 173, 35),
                Color.rgb(255,255,0),
        };
        ArrayList<Integer> colors = new ArrayList<>();
        for(int c: MY_COLORS) colors.add(c);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieDataSet.setSliceSpace(4f);
        pieData.setValueTextSize(15f);
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setTextSize(12f);
        legend.setDrawInside(false);
        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelTextSize(10f);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateXY(2000, 2000);
        pieChart.invalidate();


        //For loading images in Categories buttons
        Glide.with(this).load("https://media.cntraveler.com/photos/576af679c06bba921a97a0b8/master/w_500,h_500,c_limit/Chureito-Pagoda-E6HMHF.jpg").centerCrop().into(asiaImg);
        Glide.with(this).load("https://cdn-imgix-open.headout.com/blog/Travel/Best+Places+To+Visit+In+Europe/channel-3547224_1280.jpg?auto=compress&fm=webp&w=500&h=500&q=50").centerCrop().into(europeImg);
        Glide.with(this).load("https://i.ytimg.com/vi/QGiJFumHUPo/maxresdefault.jpg").centerCrop().into(americaImg);
        Glide.with(this).load("https://s23444.pcdn.co/wp-content/uploads/2020/01/Africa-general-pic.jpg.optimal.jpg").centerCrop().into(africaImg);
        Glide.with(this).load("https://static.tripzilla.com/thumb/e/1/116961_800x.jpg").centerCrop().into(oceaniaImg);
        Glide.with(this).load("https://images.firstpost.com/fpimages/1200x800/fixed/jpg/2018/12/ice-bergs.jpg").centerCrop().into(polarImg);
        Glide.with(this).load("https://i.pinimg.com/originals/25/df/2f/25df2fc891b938a4261420df53f02538.jpg").centerCrop().into(seeAllImg);



        listener(asia,"region/asia");
        listener(europe,"region/europe");
        listener(america,"region/americas");
        listener(africa,"region/africa");
        listener(oceania,"region/oceania");
        listener(polar,"region/polar");
        listener(seeAll,"all");

    }

    //For inserting values in Pie chart
    private ArrayList getData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "Asia(+MiddleEast)"));
        entries.add(new PieEntry(20.3f, "Africa"));
        entries.add(new PieEntry(16.3f, "North America"));
        entries.add(new PieEntry(12.0f, "South America"));
        entries.add(new PieEntry(8.9f, "Antarctica"));
        entries.add(new PieEntry(6.7f, "Europe"));
        entries.add(new PieEntry(5.2f, "Australia(+Oceania)"));
        return entries;
    }

    //For inserting values in Line graph
    private ArrayList<Entry> popChartValues(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0,528.13f));
        dataVals.add(new Entry(1,536.92f));
        dataVals.add(new Entry(2,545.34f));
        dataVals.add(new Entry(3,553.84f));
        dataVals.add(new Entry(4,562.26f));
        dataVals.add(new Entry(5,570.75f));
        dataVals.add(new Entry(6,579.05f));
        dataVals.add(new Entry(7,587.31f));
        dataVals.add(new Entry(8,595.48f));
        dataVals.add(new Entry(9,603.53f));
        dataVals.add(new Entry(10,611.51f));
        dataVals.add(new Entry(11,619.45f));
        dataVals.add(new Entry(12,627.35f));
        dataVals.add(new Entry(13,635.27f));
        dataVals.add(new Entry(14,643.24f));
        dataVals.add(new Entry(15,651.26f));
        dataVals.add(new Entry(16,659.36f));
        dataVals.add(new Entry(17,667.51f));
        dataVals.add(new Entry(18,675.79f));
        dataVals.add(new Entry(19,684.06f));
        dataVals.add(new Entry(20,692.29f));
        dataVals.add(new Entry(21,700.4f));
        dataVals.add(new Entry(22,708.7f));
        dataVals.add(new Entry(23,717.1f));
        dataVals.add(new Entry(24,725.57f));
        dataVals.add(new Entry(25,734.05f));
        dataVals.add(new Entry(26,742.61f));
        dataVals.add(new Entry(27,751.1f));
        dataVals.add(new Entry(28,759.43f));
        dataVals.add(new Entry(29,770f));
        dataVals.add(new Entry(30,780f));

        return dataVals;
    }

    //Common function for taking categories to next page of countries list
    public void listener(View v,String urlString){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CountriesList.class);
                i.putExtra("urlString",urlString);
                startActivity(i);
            }
        });
    }
}