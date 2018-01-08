package com.pccaps.pmiconference;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import android.app.Activity.*;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;
import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.Events.changeTime;
import static com.pccaps.pmiconference.PopTab3.convertTimes;
import static com.pccaps.pmiconference.PreferenceActivitySettings.selectedInt;
import static com.pccaps.pmiconference.PreferenceActivitySettings.state;
import static com.pccaps.pmiconference.Tab3.dateList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.PopTabEvents.eventChoice;
import static com.pccaps.pmiconference.clearCustomizableWarning.userClearCustomList;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab2 extends Fragment {

    static ListView eventsView;
    static List<Events> customizableList = new ArrayList<>();
    static ArrayAdapter datesAdapter;

    static int tab2Choice;

    static String intro = "To add and Event to this Page:";
    static String howToAdd = "\n1.\tGo to the schedule tab\n\n 2.\tClick on a track and then an event that you find interesting\n\n 3.\tClick the \"Add Event\" button\n\n4.\tGo to the \"SAVED EVENTS\" tab to see the event";

    static SharedPreferences prefs;
    static SharedPreferences.Editor editor;

    static List<String> properDateList = new ArrayList<>();

    Calendar time;

    static int prefTime;

    static List<Integer> startTimes = new ArrayList<>();

    static NotificationCompat.Builder preNotification;
    static final int IDB=1234;

    static NotificationCompat.Builder postNotification;
    static final int IDA=3489675;
    static final int ID1=385921;
    static final int ID2=385922;
    static final int ID3=385923;
    static final int ID4=385924;
    static final int ID5=385925;

    static RemoteViews remoteViews;

    static Events ratedEvent;

    public static boolean eventsEquals(Events e1, Events e2){
        if((e1.STime==e2.STime) && (e1.speaker.equals(e2.speaker)) && (e1.subject.equals(e2.subject)) && e1.date==e2.date){
            return true;
        }
        return false;
    }

    public static int findEvents(Events e, List<Events> tempList){
        int answer=-1;
        for(int i=0; i<tempList.size(); i++){
            if(eventsEquals(e, tempList.get(i))){
                answer=i;
                break;
            }
        }
        return answer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab2, container, false);

        properDateList.clear();

        eventsView = (ListView) rootView.findViewById(R.id.eventsView);

        remoteViews = new RemoteViews("com.pccaps.conference", R.layout.custom_notification);

        remoteViews.setImageViewResource(R.id.pmiLogo, R.drawable.icon_180);

        Intent rating1 = new Intent("ratingGiven");
        rating1.putExtra("id", ID1);
        Intent rating2 = new Intent("ratingGiven");
        rating2.putExtra("id", ID2);
        Intent rating3 = new Intent("ratingGiven");
        rating3.putExtra("id", ID3);
        Intent rating4 = new Intent("ratingGiven");
        rating4.putExtra("id", ID4);
        Intent rating5 = new Intent("ratingGiven");
        rating5.putExtra("id", ID5);

        PendingIntent intent1 = PendingIntent.getBroadcast(getContext(), 31231, rating1, 0);
        PendingIntent intent2 = PendingIntent.getBroadcast(getContext(), 31232, rating2, 0);
        PendingIntent intent3 = PendingIntent.getBroadcast(getContext(), 31233, rating3, 0);
        PendingIntent intent4 = PendingIntent.getBroadcast(getContext(), 31234, rating4, 0);
        PendingIntent intent5 = PendingIntent.getBroadcast(getContext(), 31235, rating5, 0);

        remoteViews.setOnClickPendingIntent(R.id.rate1, intent1);
        remoteViews.setOnClickPendingIntent(R.id.rate2, intent2);
        remoteViews.setOnClickPendingIntent(R.id.rate3, intent3);
        remoteViews.setOnClickPendingIntent(R.id.rate4, intent4);
        remoteViews.setOnClickPendingIntent(R.id.rate5, intent5);


        datesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, properDateList);
        eventsView.setAdapter(datesAdapter);

        /*if(userClearCustomList){
            for (int i = 0; i < customizableList.size(); i++) {
                editor.remove(String.valueOf(i));
            }
            customizableList.clear();
            //editor.clear();
            //editor.putInt(editorNotification, globalPosition);
            editor.apply();
        }*/

        eventsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tab2Choice=position;
                /*if(userClearCustomList){
                    for (int i = 0; i < prefs.getInt("customizableListSize", 0); i++) {
                        editor.remove(String.valueOf(i));
                    }
                    customizableList.clear();
                    editor.putInt("customizableListSize", 0);
                    editor.commit();
                }*/
                for(int x=0; x<prefs.getInt("customizableListSize", 0); x++){
                    if(findEvents(list.get(prefs.getInt(String.valueOf(x), 0)), customizableList)>-1){
                        continue;
                    }
                    else{
                        customizableList.add(list.get(prefs.getInt(String.valueOf(x), 0)));
                    }
                }
                startActivity(new Intent(getActivity(), PopTabCustomizable.class));
            }
        });

        for(long i:dateList){

                properDateList.add(changeDate(i));

        }

        Collections.sort(properDateList);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        eventsView.setAdapter(datesAdapter);

        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = prefs.edit();

        Collections.sort(customizableList, new Tab3.CompareEvents());

        preNotification = new NotificationCompat.Builder(this.getContext());
        preNotification.setAutoCancel(true);

        /*Thread t = new Thread(){
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
        t.start();*/
    }
}
