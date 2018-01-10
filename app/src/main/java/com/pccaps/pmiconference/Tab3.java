package com.pccaps.pmiconference;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.Events.changeTime;
//import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.PopTab3.convertTimes;
import static com.pccaps.pmiconference.PreferenceActivitySettings.state;
import static com.pccaps.pmiconference.Tab2.IDA;
import static com.pccaps.pmiconference.Tab2.IDB;
import static com.pccaps.pmiconference.Tab2.customizableDates;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.datesAdapter;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.eventsView;
import static com.pccaps.pmiconference.Tab2.firstOn;
import static com.pccaps.pmiconference.Tab2.postNotification;
import static com.pccaps.pmiconference.Tab2.preNotification;
import static com.pccaps.pmiconference.Tab2.prefTime;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab2.properDateList;
import static com.pccaps.pmiconference.Tab2.ratedEvent;
import static com.pccaps.pmiconference.Tab2.remoteViews;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab3 extends Fragment{

    //commit unversioned files

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference dRef = database.getReference("events");

    static String name;
    static long startTime;
    static long endTime;
    static String description;
    static long date;
    static String location;
    static String subject;
    static String tracks;
    static long amountPicked;

    static int countClear=0;

    int totalStart=0;

    static Object[] data = new Object[12];

    static List<String> ratedName = new ArrayList<>();
    static List<Long> ratedDate = new ArrayList<>();
    static List<Long> ratedSTime = new ArrayList<>();
    static List<String> childrenValues = new ArrayList<>();

    static int ratingsCount = 0;
    static int countCount = 0;

    static int dataTemp=0;

    static int popChoice;
    static Events temp;

    ListView listView;
    static List<Events> list = new ArrayList<>();

    static List<String> trackList = new ArrayList<>();

    static List<Long> dateList = new ArrayList<>();

    ArrayAdapter<String> adapter;

    static List<Integer> allRatings = new ArrayList<>();
    static List<Integer> allCount = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popChoice=position;
                startActivity(new Intent(getActivity(), PopTabDates.class));
            }
        });

        eventsView.setAdapter(datesAdapter);

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

        //MainActivity.toolbar.setTitle("Tracks");

        Thread t = new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(1000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Calendar cal = Calendar.getInstance();

                            SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
                            SimpleDateFormat date = new SimpleDateFormat("YYMMDD");

                            String fDate = String.valueOf(date.format(cal.getTime()));
                            String sDate = fDate.substring(2);
                            char[] tDate = sDate.toCharArray();
                            String tempDate="";

                            String fTime = String.valueOf(sdf.format(cal.getTime()));
                            String sTime = fTime.substring(0, 4);
                            char[] tTime = sTime.toCharArray();

                            int properTime=0;
                            int properDate=0;

                            for(int i=0; i<tDate.length; i++){
                                if(tDate[i]=='0' && i==0){
                                    continue;
                                }
                                tempDate+=String.valueOf(tDate[i]);
                            }
                            properDate=Integer.parseInt(tempDate);

                            for(int i=0; i<tTime.length; i++){
                                if(i%2==0){
                                    if(i==0){
                                        properTime+=600*Integer.parseInt(String.valueOf(tTime[i]));
                                    }
                                    else{
                                        properTime+=10*Integer.parseInt(String.valueOf(tTime[i]));
                                    }
                                }
                                else{
                                    if(i==1){
                                        properTime+=60*Integer.parseInt(String.valueOf(tTime[i]));
                                    }
                                    else{
                                        properTime+=Integer.parseInt(String.valueOf(tTime[i]));
                                    }
                                }
                            }
                            if(state==0){

                            }
                            else{
                                prefTime=prefs.getInt("prefTime", 10);

                                for(Events e:customizableList){
                                    if(e.getDate()==properDate){
                                        int sTemp = convertTimes(e.getSTime());
                                        int eTemp = convertTimes((int)e.ETime);
                                        if(sTemp-properTime<=prefTime && sTemp-properTime>0){
                                            preNotification.setSmallIcon(R.mipmap.pmi_launcher);
                                            preNotification.setTicker("An Event is Starting");
                                            preNotification.setWhen(System.currentTimeMillis());
                                            preNotification.setContentTitle("One of Your Events is Starting Soon");
                                            preNotification.setContentText("You have an event starting in "+ String.valueOf(sTemp-properTime) + " minutes.");

                                            Intent intent = new Intent(getContext(), Tab2.class);
                                            PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                            preNotification.setContentIntent(pendingIntent);

                                            NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                            manager.notify(IDB, preNotification.build());
                                        }
                                        else if(properTime>=eTemp && properTime-eTemp<=60){
                                            Intent notification_intent = new Intent(getContext(), Tab2.class);
                                            PendingIntent pendingNotification = PendingIntent.getActivity(getContext(), 0, notification_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                                            remoteViews.setTextViewText(R.id.text, "Please rate the event you watched at "+changeTime(e.getSTime())+", "+changeDate(e.getDate())+".");


                                            postNotification = new NotificationCompat.Builder(getContext());
                                            postNotification.setSmallIcon(R.drawable.pmi_logo)
                                                    .setAutoCancel(true)
                                                    .setCustomBigContentView(remoteViews)
                                                    .setContentIntent(pendingNotification);

                                            NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                            manager.notify(IDA, postNotification.build());

                                            ratedEvent = new Events(e.speaker, e.STime, e.ETime, e.P, e.D, e.subject, e.date, e.tracks);
                                        }
                                    }
                                }
                            }
                        }
                    });
                }catch(InterruptedException e){

                }
            }
        };
        t.start();

        //datesAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_list, android.R.id.text1, properDateList);
        eventsView.setAdapter(datesAdapter);

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();
                trackList.clear();
                dateList.clear();
                ratedDate.clear();
                ratedSTime.clear();
                ratedName.clear();
                allRatings.clear();
                ratingsCount=0;

                for(final DataSnapshot snapshot : dataSnapshot.getChildren() ){

                    snapshot.getRef().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if(String.valueOf(snapshot.getRef()).equals("https://pmiconferencedatabase.firebaseio.com/events/event1")){
                                list.clear();
                            }

                            properDateList.clear();
                            ratingsCount=0;

                            for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                                Object retrieve = snapshot.getValue();
                                if(String.valueOf(snapshot.getKey()).equals("ratings")){
                                    for(int i=0; i<(int)snapshot.getChildrenCount(); i++){
                                        ratingsCount++;
                                        editor.putInt("ratingNum", ratingsCount);
                                        editor.apply();
                                    }
                                    allRatings.add(ratingsCount);
                                }
                                if(String.valueOf(snapshot.getKey()).equals("count")){
                                    countCount=Integer.parseInt(String.valueOf(retrieve));
                                    allCount.add(countCount);
                                }
                                data[dataTemp]=retrieve;
                                dataTemp++;
                            }
                            dataTemp=0;
                            amountPicked = (long) data[0];
                            name = (String) data[5];
                            startTime = (long) data[9];
                            endTime = (long) data[8];
                            description = (String) data[2];
                            date = (long) data[1];
                            location = (String) data[4];
                            subject = (String) data[7];
                            tracks = (String) data[10];

                            ratedName.add(name);
                            ratedDate.add(date);
                            ratedSTime.add(startTime);
                            childrenValues.add(snapshot.getKey().toString());

                            if(!trackList.contains(tracks)){
                                trackList.add(tracks);
                            }
                            if(!dateList.contains(date)){
                                dateList.add(date);
                            }

                            Collections.sort(dateList);

                            Collections.sort(trackList);

                            for(long i:dateList){
                                properDateList.add(changeDate(i));
                            }

                            Collections.sort(properDateList);

                            //datesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, properDateList);
                            eventsView.setAdapter(datesAdapter);

                            temp = new Events(name, startTime, endTime, location, description, subject, date, tracks);
                            list.add(temp);

                            Collections.sort(list, new CompareEvents());

                            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, trackList);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static class CompareEvents implements Comparator<Events>{

        @Override
        public int compare(Events events, Events t1) {
            if(events.getDate()==t1.getDate()){
                return (events.getSTime() - t1.getSTime());
            }
            else{
                return events.getDate()-t1.getDate();
            }
        }
    }
}

