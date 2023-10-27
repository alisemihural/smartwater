package com.example.smartwater;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

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

public class myService extends Service {
    DatabaseReference ref;

    public String total2;
    public String total1;
    public float total3;

    public int total1int;
    public int total2int;

    public int total1day;
    public int total2day;
    public float total3day;

    public float day1,day2,day3,day4,day5,day6,day7,day8,day9,day10,day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,day21,day22,day23,day24,day25,day26,day27,day28,day29,day30;

    AnyChartView monthlyChartView;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground();

        ref = FirebaseDatabase.getInstance().getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                total1=snapshot.child("Total1").getValue().toString();
                total2=snapshot.child("Total2").getValue().toString();

                total3 = (Float.valueOf(total1)+Float.valueOf(total2))/1000;

                total1int = snapshot.child("Total1").getValue(Integer.class);
                total2int = snapshot.child("Total2").getValue(Integer.class);

                total1day = snapshot.child("Total1day").getValue(Integer.class);
                total2day = snapshot.child("Total2day").getValue(Integer.class);

                total3day = (Float.valueOf(total1day)+Float.valueOf(total2day))/1000;


                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                Cartesian kartezyen = AnyChart.column();

                List<DataEntry> data = new ArrayList<>();


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
                    database.getReference("zday1").setValue(total3day);
                    day1=Float.valueOf(snapshot.child("zday1").getValue().toString());
                    data.add(new ValueDataEntry(1,day1));
                }
                else if (day==2){
                    database.getReference("zday2").setValue(total3day);
                    day2=Float.valueOf(snapshot.child("zday2").getValue().toString());
                    data.add(new ValueDataEntry(2,day2));
                }
                else if (day==3){
                    database.getReference("zday3").setValue(total3day);
                    day3=Float.valueOf(snapshot.child("zday3").getValue().toString());
                    data.add(new ValueDataEntry(3,day3));
                }
                else if (day==4){
                    database.getReference("zday4").setValue(total3day);
                    day4=Float.valueOf(snapshot.child("zday4").getValue().toString());
                    data.add(new ValueDataEntry(4,day4));
                }
                else if (day==5){
                    database.getReference("zday5").setValue(total3day);
                    day5=Float.valueOf(snapshot.child("zday5").getValue().toString());
                    data.add(new ValueDataEntry(5,day5));
                }
                else if (day==6){
                    database.getReference("zday6").setValue(total3day);
                    day6=Float.valueOf(snapshot.child("zday6").getValue().toString());
                    data.add(new ValueDataEntry(6,day6));
                }
                else if (day==7){
                    database.getReference("zday7").setValue(total3day);
                    day7=Float.valueOf(snapshot.child("zday7").getValue().toString());
                    data.add(new ValueDataEntry(7,day7));
                }
                else if (day==8){
                    database.getReference("zday8").setValue(total3day);
                    day8=Float.valueOf(snapshot.child("zday8").getValue().toString());
                    data.add(new ValueDataEntry(8,day8));
                }
                else if (day==9){
                    database.getReference("zday9").setValue(total3day);
                    day9=Float.valueOf(snapshot.child("zday9").getValue().toString());
                    data.add(new ValueDataEntry(9,day9));
                }
                else if (day==10){
                    database.getReference("zday10").setValue(total3day);
                    day10=Float.valueOf(snapshot.child("zday10").getValue().toString());
                    data.add(new ValueDataEntry(10,day10));
                }
                else if (day==11){
                    database.getReference("zday11").setValue(total3day);
                    day11=Float.valueOf(snapshot.child("zday11").getValue().toString());
                    data.add(new ValueDataEntry(11,day11));
                }
                else if (day==12){
                    database.getReference("zday12").setValue(total3day);
                    day12=Float.valueOf(snapshot.child("zday12").getValue().toString());
                    data.add(new ValueDataEntry(12,day12));
                }
                else if (day==13){
                    database.getReference("zday13").setValue(total3day);
                    day13=Float.valueOf(snapshot.child("zday13").getValue().toString());
                    data.add(new ValueDataEntry(13,day13));
                }
                else if (day==14){
                    database.getReference("zday14").setValue(total3day);
                    day14=Float.valueOf(snapshot.child("zday14").getValue().toString());
                    data.add(new ValueDataEntry(14,day14));
                }
                else if (day==15){
                    database.getReference("zday15").setValue(total3day);
                    day15=Float.valueOf(snapshot.child("zday15").getValue().toString());
                    data.add(new ValueDataEntry(15,day15));
                }
                else if (day==16){
                    database.getReference("zday16").setValue(total3day);
                    day16=Float.valueOf(snapshot.child("zday16").getValue().toString());
                    data.add(new ValueDataEntry(16,day16));
                }
                else if (day==17){
                    database.getReference("zday17").setValue(total3day);
                    day17=Float.valueOf(snapshot.child("zday17").getValue().toString());
                    data.add(new ValueDataEntry(17,day17));
                }
                else if (day==18){
                    database.getReference("zday18").setValue(total3day);
                    day18=Float.valueOf(snapshot.child("zday18").getValue().toString());
                    data.add(new ValueDataEntry(18,day18));
                }
                else if (day==19){
                    database.getReference("zday19").setValue(total3day);
                    day19=Float.valueOf(snapshot.child("zday19").getValue().toString());
                    data.add(new ValueDataEntry(19,day19));
                }
                else if (day==20){
                    database.getReference("zday20").setValue(total3day);
                    day20=Float.valueOf(snapshot.child("zday20").getValue().toString());
                    data.add(new ValueDataEntry(20,day20));
                }
                else if (day==21){
                    database.getReference("zday21").setValue(total3day);
                    day21=Float.valueOf(snapshot.child("zday21").getValue().toString());
                    data.add(new ValueDataEntry(21,day21));
                }
                else if (day==22){
                    database.getReference("zday22").setValue(total3day);
                    day22=Float.valueOf(snapshot.child("zday22").getValue().toString());
                    data.add(new ValueDataEntry(22,day22));
                }
                else if (day==23){
                    database.getReference("zday23").setValue(total3day);
                    day23=Float.valueOf(snapshot.child("zday23").getValue().toString());
                    data.add(new ValueDataEntry(23,day23));
                }
                else if (day==24){
                    database.getReference("zday24").setValue(total3day);
                    day24=Float.valueOf(snapshot.child("zday24").getValue().toString());
                    data.add(new ValueDataEntry(24,day24));
                }
                else if (day==25){
                    database.getReference("zday25").setValue(total3day);
                    day25=Float.valueOf(snapshot.child("zday25").getValue().toString());
                    data.add(new ValueDataEntry(25,day25));
                }
                else if (day==26){
                    database.getReference("zday26").setValue(total3day);
                    day26=Float.valueOf(snapshot.child("zday26").getValue().toString());
                    data.add(new ValueDataEntry(26,day26));
                }
                else if (day==27){
                    database.getReference("zday27").setValue(total3day);
                    day27=Float.valueOf(snapshot.child("zday27").getValue().toString());
                    data.add(new ValueDataEntry(27,day27));
                }
                else if (day==28){
                    database.getReference("zday28").setValue(total3day);
                    day28=Float.valueOf(snapshot.child("zday28").getValue().toString());
                    data.add(new ValueDataEntry(28,day28));
                }
                else if (day==29){
                    database.getReference("zday29").setValue(total3day);
                    day29=Float.valueOf(snapshot.child("zday29").getValue().toString());
                    data.add(new ValueDataEntry(29,day29));
                }
                else if (day==30){
                    database.getReference("zday30").setValue(total3day);
                    day30=Float.valueOf(snapshot.child("zday30").getValue().toString());
                    data.add(new ValueDataEntry(30,day30));
                }


                Column sutun = kartezyen.column(data);
                monthlyChartView.setChart(kartezyen);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // do your jobs here
        return super.onStartCommand(intent, flags, startId);
    }
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";
    private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setContentTitle("SmartWater")
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());
    }
}
