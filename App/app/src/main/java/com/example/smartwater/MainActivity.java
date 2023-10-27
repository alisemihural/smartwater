package com.example.smartwater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    DatabaseReference ref;
    TextView a,b,c,d,e,f;
    public float total3;
    public double TL;
    public int cities;
    public String strTL;
    public int spinnerN;
    public int edittextN;
    public int edittextNC;

    public String total2;
    public String total1;

    public String hiz1;
    public String hiz2;

    public int total1int;
    public int total2int;

    public int total1day;
    public int total2day;
    public float total3day;

    public static final String SharedPrefs = "sharedPrefs";
    public static final String text = "text1";
    public static final String text2 = "text2";
    public static final String text3 = "text3";

    private int sehir;
    private int birim;
    private int sinir;
    private int günS;

    public String totallitre;
    public Intent serviceIntent;

    public float days[];

    public static final String text4="text4", txt1 ="txt1",txt2 = "txt2",txt3 = "txt3",txt4 = "txt4",txt5 = "txt5",txt6 = "txt6",txt7 = "txt7",txt8 = "txt8",txt9 = "txt9",txt10 = "txt10",txt11 = "txt11",txt12 = "txt12",txt13 = "txt13",txt14 = "txt14",txt15 = "txt15",txt16 = "txt16",txt17 = "txt17",txt18 = "txt18",txt19 = "txt19",txt20 = "txt20",txt21 = "txt21",txt22 = "txt22",txt23 = "txt23",txt24 = "txt24",txt25 = "txt25",txt26 = "txt26",txt27 = "txt27",txt28 = "txt28",txt29 = "txt29",txt30 = "txt30";
    public float day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,day21,day22,day23,day24,day25,day26,day27,day28,day29,day30;
    public int dayC=0;
    private Handler mHandler = new Handler();

    AnyChartView monthlyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideNavigationBar();

        totallitre = Float.toString(total3);

        serviceIntent = new Intent(this,SmartWaterService.class);



        final int random = new Random().nextInt(6) + 1;

        a=(TextView)findViewById(R.id.textView2);
        b=(TextView)findViewById(R.id.textView);
        c=(TextView)findViewById(R.id.textView9);
        d=(TextView)findViewById(R.id.textView3);
        e=(TextView)findViewById(R.id.textView10);
        f=(TextView)findViewById(R.id.textView8);

        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner)findViewById(R.id.spinner3);

        ref = FirebaseDatabase.getInstance().getReference();

        EditText sinirsayiET = (EditText) findViewById(R.id.editTextNumber);

        monthlyChartView = findViewById(R.id.monthlyChart);

        monthlyChartView.getChildAt(1).setVisibility(View.INVISIBLE);

        monthlyChartView.invalidate();


        loadData();
        updateData();


        if (random==1){
            d.setText("Save water today or tomorrow you’ll pay.");
        }
        else if (random==2){
            d.setText("Conserve water, conserve life.");
        }
        else if (random==3){
            d.setText("Don't let life slip down the drain.");
        }
        else if (random==4){
            d.setText("You never know the worth of water until the well runs dry.");
        }
        else if (random==5){
            d.setText("Don’t be the leak in our global flow.");
        }


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                total1=snapshot.child("Total1").getValue().toString();
                total2=snapshot.child("Total2").getValue().toString();

                total3 = (Float.valueOf(total1)+Float.valueOf(total2))/1000;

                total1int = snapshot.child("Total1").getValue(Integer.class);
                total2int = snapshot.child("Total2").getValue(Integer.class);

                total1day = snapshot.child("Total1day").getValue(Integer.class);
                total2day = snapshot.child("Total2day").getValue(Integer.class);

                total3day = (Float.valueOf(total1day)+Float.valueOf(total2day))/1000;

                a.setText(Float.toString(total3));

                hiz1 = snapshot.child("Hiz1").getValue().toString();
                hiz2 = snapshot.child("Hiz2").getValue().toString();

                e.setText(hiz1);
                c.setText(hiz2);


                if (spinnerN==1){
                    china();
                }
                else if (spinnerN==2){
                    england();
                }
                else if (spinnerN==3){
                    germany();
                }
                else if (spinnerN==4){
                    japan();
                }
                else if (spinnerN==5){
                    sweden();
                }
                else if (spinnerN==6){
                    turkey();
                }
                else if (spinnerN==7){
                    usa();
                }

                if (edittextN==1){
                    if (edittextNC==1){
                        EditText sinirsayiET = (EditText) findViewById(R.id.editTextNumber);
                        if (TL>=Integer.valueOf(sinirsayiET.getText().toString())){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            ref = database.getReference("PressureChamber");

                            ref.setValue(1);
                            sinirOff();


                        }
                    }
                }
                else if (edittextN==2){
                    if (edittextNC==1) {
                        EditText sinirsayiET = (EditText) findViewById(R.id.editTextNumber);
                        if (total3 >= Integer.valueOf(sinirsayiET.getText().toString())) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            ref = database.getReference("PressureChamber");

                            ref.setValue(1);

                        }
                    }
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                List<PieEntry> kategoriEntries = new ArrayList<>();
                kategoriEntries.add(new PieEntry(total1int,"Bathroom"));
                kategoriEntries.add(new PieEntry(total2int,"Kitchen"));
                PieDataSet kategoriDataSet = new PieDataSet(kategoriEntries,"");
                PieData kategoriData = new PieData(kategoriDataSet);
                int[] colorClassArray = new int[]{Color.BLUE,Color.CYAN,Color.RED,Color.DKGRAY};
                kategoriDataSet.setColors(colorClassArray);

                PieChart kategoriChart = (PieChart)  findViewById(R.id.kategoriChart);
                kategoriChart.getDescription().setEnabled(false);
                kategoriChart.setData(kategoriData);
                kategoriChart.invalidate();


                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                Cartesian kartezyen = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();


                day1=Float.valueOf(snapshot.child("zday1").getValue().toString());
                day2=Float.valueOf(snapshot.child("zday2").getValue().toString());
                day3=Float.valueOf(snapshot.child("zday3").getValue().toString());
                day4=Float.valueOf(snapshot.child("zday4").getValue().toString());
                day5=Float.valueOf(snapshot.child("zday5").getValue().toString());
                day6=Float.valueOf(snapshot.child("zday6").getValue().toString());
                day7=Float.valueOf(snapshot.child("zday7").getValue().toString());
                day8=Float.valueOf(snapshot.child("zday8").getValue().toString());
                day9=Float.valueOf(snapshot.child("zday9").getValue().toString());
                day10=Float.valueOf(snapshot.child("zday10").getValue().toString());
                day11=Float.valueOf(snapshot.child("zday11").getValue().toString());
                day12=Float.valueOf(snapshot.child("zday12").getValue().toString());
                day13=Float.valueOf(snapshot.child("zday13").getValue().toString());
                day14=Float.valueOf(snapshot.child("zday14").getValue().toString());
                day15=Float.valueOf(snapshot.child("zday15").getValue().toString());
                day16=Float.valueOf(snapshot.child("zday16").getValue().toString());
                day17=Float.valueOf(snapshot.child("zday17").getValue().toString());
                day18=Float.valueOf(snapshot.child("zday18").getValue().toString());
                day19=Float.valueOf(snapshot.child("zday19").getValue().toString());
                day20=Float.valueOf(snapshot.child("zday20").getValue().toString());
                day21=Float.valueOf(snapshot.child("zday21").getValue().toString());
                day22=Float.valueOf(snapshot.child("zday22").getValue().toString());
                day23=Float.valueOf(snapshot.child("zday23").getValue().toString());
                day24=Float.valueOf(snapshot.child("zday24").getValue().toString());
                day25=Float.valueOf(snapshot.child("zday25").getValue().toString());
                day26=Float.valueOf(snapshot.child("zday26").getValue().toString());
                day27=Float.valueOf(snapshot.child("zday27").getValue().toString());
                day28=Float.valueOf(snapshot.child("zday28").getValue().toString());
                day29=Float.valueOf(snapshot.child("zday29").getValue().toString());
                day30=Float.valueOf(snapshot.child("zday30").getValue().toString());


                data.add(new ValueDataEntry(1,day1));
                data.add(new ValueDataEntry(2,day2));
                data.add(new ValueDataEntry(3,day3));
                data.add(new ValueDataEntry(4,day4));
                data.add(new ValueDataEntry(5,day5));
                data.add(new ValueDataEntry(6,day6));
                data.add(new ValueDataEntry(7,day7));
                data.add(new ValueDataEntry(8,day8));
                data.add(new ValueDataEntry(9,day9));
                data.add(new ValueDataEntry(10,day10));

                data.add(new ValueDataEntry(11,day11));
                data.add(new ValueDataEntry(12,day12));
                data.add(new ValueDataEntry(13,day13));
                data.add(new ValueDataEntry(14,day14));
                data.add(new ValueDataEntry(15,day15));
                data.add(new ValueDataEntry(16,day16));
                data.add(new ValueDataEntry(17,day17));
                data.add(new ValueDataEntry(18,day18));
                data.add(new ValueDataEntry(19,day19));
                data.add(new ValueDataEntry(20,day20));

                data.add(new ValueDataEntry(21,day21));
                data.add(new ValueDataEntry(22,day22));
                data.add(new ValueDataEntry(23,day23));
                data.add(new ValueDataEntry(24,day24));
                data.add(new ValueDataEntry(25,day25));
                data.add(new ValueDataEntry(26,day26));
                data.add(new ValueDataEntry(27,day27));
                data.add(new ValueDataEntry(28,day28));
                data.add(new ValueDataEntry(29,day29));
                data.add(new ValueDataEntry(30,day30));




                if (day==1){
                    if(dayC!=1){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    database.getReference("Total1").setValue(0);
                    database.getReference("Total2").setValue(0);

                    database.getReference("zday1").setValue(0);
                    database.getReference("zday2").setValue(0);
                    database.getReference("zday3").setValue(0);
                    database.getReference("zday4").setValue(0);
                    database.getReference("zday5").setValue(0);
                    database.getReference("zday6").setValue(0);
                    database.getReference("zday7").setValue(0);
                    database.getReference("zday8").setValue(0);
                    database.getReference("zday9").setValue(0);
                    database.getReference("zday10").setValue(0);
                    database.getReference("zday11").setValue(0);
                    database.getReference("zday12").setValue(0);
                    database.getReference("zday13").setValue(0);
                    database.getReference("zday14").setValue(0);
                    database.getReference("zday15").setValue(0);
                    database.getReference("zday16").setValue(0);
                    database.getReference("zday17").setValue(0);
                    database.getReference("zday18").setValue(0);
                    database.getReference("zday19").setValue(0);
                    database.getReference("zday20").setValue(0);
                    database.getReference("zday21").setValue(0);
                    database.getReference("zday22").setValue(0);
                    database.getReference("zday23").setValue(0);
                    database.getReference("zday24").setValue(0);
                    database.getReference("zday25").setValue(0);
                    database.getReference("zday26").setValue(0);
                    database.getReference("zday27").setValue(0);
                    database.getReference("zday28").setValue(0);
                    database.getReference("zday29").setValue(0);
                    database.getReference("zday30").setValue(0);

                    dayC=1;
                    database.getReference("zday1").setValue(total3day);
                    data.add(new ValueDataEntry(1,day1));
                }
               else if (day==2){
                    if(dayC!=2){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=2;
                    database.getReference("zday2").setValue(total3day);
                    data.add(new ValueDataEntry(2,day2));
                }
                else if (day==3){
                    if(dayC!=3){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=3;
                    database.getReference("zday3").setValue(total3day);
                    data.add(new ValueDataEntry(3,day3));
                }
                else if (day==4){
                    if(dayC!=4){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=4;
                    database.getReference("zday4").setValue(total3day);
                    data.add(new ValueDataEntry(4,day4));
                }
                else if (day==5){
                    if(dayC!=5){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=5;
                    database.getReference("zday5").setValue(total3day);
                    data.add(new ValueDataEntry(5,day5));
                }
                else if (day==6){
                    if(dayC!=6){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=6;
                    database.getReference("zday6").setValue(total3day);
                    data.add(new ValueDataEntry(6,day6));
                }
                else if (day==7){
                    if(dayC!=7){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=7;
                    database.getReference("zday7").setValue(total3day);
                    data.add(new ValueDataEntry(7,day7));
                }
                else if (day==8){
                    if(dayC!=8){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=8;
                    database.getReference("zday8").setValue(total3day);
                    data.add(new ValueDataEntry(8,day8));
                }
                else if (day==9){
                    if(dayC!=9){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=9;
                    database.getReference("zday9").setValue(total3day);
                    data.add(new ValueDataEntry(9,day9));
                }
                else if (day==10){
                    if(dayC!=10){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=10;
                    database.getReference("zday10").setValue(total3day);
                    data.add(new ValueDataEntry(10,day10));
                }
                else if (day==11){
                    if(dayC!=11){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=11;
                    database.getReference("zday11").setValue(total3day);
                    data.add(new ValueDataEntry(11,day11));
                }
                else if (day==12){
                    if(dayC!=12){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=12;
                    database.getReference("zday12").setValue(total3day);
                    data.add(new ValueDataEntry(12,day12));
                }
                else if (day==13){
                    if(dayC!=13){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=13;
                    database.getReference("zday13").setValue(total3day);
                    data.add(new ValueDataEntry(13,day13));
                }
                else if (day==14){
                    if(dayC!=14){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=14;
                    database.getReference("zday14").setValue(total3day);
                    data.add(new ValueDataEntry(14,day14));
                }
                else if (day==15){
                    if(dayC!=15){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=15;
                    database.getReference("zday15").setValue(total3day);
                    data.add(new ValueDataEntry(15,day15));
                }
                else if (day==16){
                    if(dayC!=16){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=16;
                    database.getReference("zday16").setValue(total3day);
                    data.add(new ValueDataEntry(16,day16));
                }
                else if (day==17){
                    if(dayC!=17){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=17;
                    database.getReference("zday17").setValue(total3day);
                    data.add(new ValueDataEntry(17,day17));
                }
                else if (day==18){
                    if(dayC!=18){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=18;
                    database.getReference("zday18").setValue(total3day);
                    data.add(new ValueDataEntry(18,day18));
                }
                else if (day==19){
                    if(dayC!=19){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=19;
                    database.getReference("zday19").setValue(total3day);
                    data.add(new ValueDataEntry(19,day19));
                }
                else if (day==20){
                    if(dayC!=20){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=20;
                    database.getReference("zday20").setValue(total3day);
                    data.add(new ValueDataEntry(20,day20));
                }
                else if (day==21){
                    if(dayC!=21){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=21;
                    database.getReference("zday21").setValue(total3day);
                    data.add(new ValueDataEntry(21,day21));
                }
                else if (day==22){
                    if(dayC!=22){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=22;
                    database.getReference("zday22").setValue(total3day);
                    data.add(new ValueDataEntry(22,day22));
                }
                else if (day==23){
                    if(dayC!=23){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=23;
                    database.getReference("zday23").setValue(total3day);
                    data.add(new ValueDataEntry(23,day23));
                }
                else if (day==24){
                    if(dayC!=24){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=24;
                    database.getReference("zday24").setValue(total3day);
                    data.add(new ValueDataEntry(24,day24));
                }
                else if (day==25){
                    if(dayC!=25){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=25;
                    database.getReference("zday25").setValue(total3day);
                    data.add(new ValueDataEntry(25,day25));
                }
                else if (day==26){
                    if(dayC!=26){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=26;
                    database.getReference("zday26").setValue(total3day);
                    data.add(new ValueDataEntry(26,day26));
                }
                else if (day==27){
                    if(dayC!=27){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=27;
                    database.getReference("zday27").setValue(total3day);
                    data.add(new ValueDataEntry(27,day27));
                }
                else if (day==28){
                    if(dayC!=28){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=28;
                    database.getReference("zday28").setValue(total3day);
                    data.add(new ValueDataEntry(28,day28));
                }
                else if (day==29){
                    if(dayC!=29){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=29;
                    database.getReference("zday29").setValue(total3day);
                    data.add(new ValueDataEntry(29,day29));
                }
                else if (day==30){
                    if(dayC!=30){
                        database.getReference("Total1day").setValue(0);
                        database.getReference("Total2day").setValue(0);
                    }
                    dayC=30;
                    database.getReference("zday30").setValue(total3day);
                    data.add(new ValueDataEntry(30,day30));
                }


                Column sutun = kartezyen.column(data);
                monthlyChartView.setChart(kartezyen);



                serviceIntent.putExtra("inputExtra",Float.toString(total3));

                startService(serviceIntent);
                saveData();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,  R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,  R.array.birim, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id==1){
                    edittextN=1;
                    saveData();
                }
                else if(id==2){
                    edittextN=2;
                    saveData();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sinirsayiET.onBeginBatchEdit();
    }


    public void monthlyChartButton(View view){
        monthlyChartView.getChildAt(1).setVisibility(View.INVISIBLE);
        monthlyChartView.getChildAt(0).setVisibility(View.VISIBLE);
    }

    public void kategoriChartButton(View view){
        monthlyChartView.getChildAt(1).setVisibility(View.VISIBLE);
        monthlyChartView.getChildAt(0).setVisibility(View.INVISIBLE);
    }


    public void modeButton(View view){
        PCOn();
        sinirOff();
    }

    public void modeOff(View view) {
        PCOff();
        sinirOff();
    }

    public void PCOn() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("PressureChamber");

        ref.setValue(1);
        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
    }
    public void PCOff() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("PressureChamber");

        ref.setValue(2);
        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
    }
    public void sinirOn(){
        edittextNC=1;
        TextView sinitrtxt = findViewById(R.id.textView4);
        sinitrtxt.setText("On");
    }
    public void sinirOff(){
        edittextNC=0;
        TextView sinitrtxt = findViewById(R.id.textView4);
        EditText sinirsayiET = (EditText) findViewById(R.id.editTextNumber);

        sinitrtxt.setText("Off");

    }
    public void tamamButton(View view){
        saveData();
        sinirOn();
    }

    private void  hideNavigationBar(){
        this.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
    }




    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText sinirsayiET = (EditText) findViewById(R.id.editTextNumber);
        final Spinner spinner2 = (Spinner)findViewById(R.id.spinner3);
        editor.putInt(text, spinnerN);
        editor.putInt(text2, edittextN);
        editor.putInt(text3,Integer.valueOf(sinirsayiET.getText().toString()));
        editor.putInt(text4,dayC);

        editor.apply();

    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sehir = sharedPreferences.getInt(text, 0);
        birim = sharedPreferences.getInt(text2, 0);
        sinir = sharedPreferences.getInt(text3, 0);
        günS = sharedPreferences.getInt(text4,0);

    }
    public void updateData(){
        final Spinner spinner2 = (Spinner)findViewById(R.id.spinner3);
        EditText sinirsayiET = (EditText) findViewById(R.id.editTextNumber);
        spinnerN = sehir;
        edittextN = birim;
        sinirsayiET.setText(String.valueOf(sinir));
        dayC = günS;

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int cities = (int) parent.getSelectedItemId();
        if (id==1){
            spinnerN=1;
            china();
            saveData();
        }
        else if(id==2){
            spinnerN=2;
            england();
            saveData();
        }
        else if(id== 3){
            spinnerN=3;
            germany();
            saveData();
        }
        else if(id==4){
            spinnerN=4;
            japan();
            saveData();
        }
        else if(id==5){
            spinnerN=5;
            sweden();
            saveData();
        }
        else if(id==6){
            spinnerN=6;
            turkey();
            saveData();
        }
        else if(id==7){
            spinnerN=7;
            usa();
            saveData();
        }

    }
    //China 2¥/m3 no tax
    public void china(){
        double chinaSuTL = total3*0.002;
        double chinaTotalTL = chinaSuTL;
        TL = chinaTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("¥");
    }
    //England 1,38£/m3 monthly tax 1.58
    public void england(){
        double englandSuTL = total3*0.00138;
        double englandKDV= 1.58;
        double englandTotalTL = englandSuTL+englandKDV;
        TL = englandTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("£");
    }
   //Germany 5€/m3 tax included
    public void germany(){
        double germanySuTL = total3*0.005;
        double germanyTotalTL = germanySuTL;
        TL = germanyTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("€");
    }
    //Japan 218¥/m3 tax included
    public void japan(){
        double japanSuTL = total3*0.218;
        double japanTotalTL = japanSuTL;
        TL = japanTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("¥");
    }
    //Sweden 36.24kr/m3
    public void sweden(){
        double swedenSuTL = total3*0.03624;
        double swedenTotalTL = swedenSuTL;
        TL = swedenTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("kr");
    }
    //Turkey 3,33TL/m3
    public void turkey(){
        double turkeySuTL = total3*0.00333;
        double turkeyKDV= (turkeySuTL*8)/100;
        double turkeyTotalTL = turkeySuTL+turkeyKDV;
        TL = turkeyTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("₺");
    }
    //USA 1.28$/m3
    public void usa(){
        double usaSuTL = total3*0.00128;
        double usaKDV= (usaSuTL*15)/100;
        double usaTotalTL = usaSuTL+usaKDV;
        TL = usaTotalTL;
        strTL = Double.toString(TL);
        b.setText(strTL);
        f.setText("$");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
