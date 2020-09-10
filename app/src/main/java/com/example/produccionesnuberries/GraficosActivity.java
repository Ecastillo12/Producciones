package com.example.produccionesnuberries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class GraficosActivity extends AppCompatActivity {
    //private BarChart barChart;
    private PieChart pieChart;
    private String[]months=new String[]{"Diario","Semanal","Mensual"};
    private int[]sales=new int[3];
    private int[]colors=new int[]{Color.rgb(245,169,225),Color.rgb(169,169,245),Color.rgb(247,81,81)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        pieChart=(PieChart)findViewById(R.id.pieChart);
        //barChart=(BarChart)findViewById(R.id.barChart);
        Intent intent = getIntent();
            sales=intent.getIntArrayExtra("prod");
            if(sales!=null) {
                Toast.makeText(GraficosActivity.this, "BIEN", Toast.LENGTH_SHORT).show();
                createCharts(sales);
            }else{
                Toast.makeText(GraficosActivity.this, "MAL", Toast.LENGTH_SHORT).show();
            }

    }
    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY){
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(20);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }

    private void legend(Chart chart){
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(20);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        ArrayList<LegendEntry>entries=new ArrayList<>();
        for (int i=0;i<months.length;i++){
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors[i];
            entry.label=months[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    private ArrayList<BarEntry> getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0;i<sales.length;i++)
            entries.add(new BarEntry(i,sales[i]));
        return entries;
    }
    private ArrayList<PieEntry> getPieEntries(int[] valores){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0;i<valores.length;i++)
            entries.add(new PieEntry(valores[i]));
        return entries;
    }
    private void axisX(XAxis xAxis){
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
    }
    private void axisLeft(YAxis yAxis){
        yAxis.setSpaceTop(30);
        yAxis.setAxisMinimum(0);
    }
    private void axisRight(YAxis yAxis){
        yAxis.setEnabled(false);
    }
    public void createCharts(int[] valores){
        /*
        barChart=(BarChart)getSameChart(barChart,"Series",Color.RED,Color.CYAN,3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());*/

        pieChart=(PieChart)getSameChart(pieChart,"Producción",Color.GRAY,Color.WHITE,3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData(valores));//Aqui recibe los datos
        pieChart.invalidate();
        //pieChart.setDrawHoleEnabled(false);

    }
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(15);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }
    private PieData getPieData(int[] valores){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(valores),""));//getPieEntries recibe los datos en forma de ArrayList

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}
