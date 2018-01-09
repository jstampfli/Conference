package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.firebase.database.*;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.MutableData;
//import com.google.firebase.database.Transaction;
import com.google.gson.Gson;

import org.json.JSONArray;

import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.PopTabEvents.eventsTrack;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.eventsEquals;
import static com.pccaps.pmiconference.Tab2.findEvents;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab2.ratedEvent;
import static com.pccaps.pmiconference.Tab2.startTimes;
import static com.pccaps.pmiconference.Tab3.allCount;
import static com.pccaps.pmiconference.Tab3.allRatings;
import static com.pccaps.pmiconference.Tab3.amountPicked;
import static com.pccaps.pmiconference.Tab3.childrenValues;
import static com.pccaps.pmiconference.Tab3.countCount;
import static com.pccaps.pmiconference.Tab3.dRef;
import static com.pccaps.pmiconference.Tab3.database;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.name;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.PopTabEvents.eventChoice;
import static com.pccaps.pmiconference.Tab3.ratedDate;
import static com.pccaps.pmiconference.Tab3.ratedName;
import static com.pccaps.pmiconference.Tab3.ratedSTime;
import static com.pccaps.pmiconference.Tab3.startTime;
import static com.pccaps.pmiconference.clearCustomizableWarning.userClearCustomList;

/**
 * Created by User1 on 10/3/2017.
 */

public class PopTab3 extends AppCompatActivity{

    //comment created so i could submit unversioned files
    TextView textViewBlowup;

    Context context = this;
    String text = "Event Added";
    int duration = Toast.LENGTH_SHORT;

    Toast toast;

    static String countChild;
    static int countValue;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindowtab3);

        MainActivity.toolbar.setTitle("Details");

        toast = Toast.makeText(context, text, duration);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width =dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout(width,height);

        textViewBlowup = (TextView) findViewById(R.id.blowup);
        textViewBlowup.setText(
                "\n\n"+eventsTrack.get(eventChoice).subject+"\n\n"+eventsTrack.get(eventChoice).speaker+"\n\n"+eventsTrack.get(eventChoice).P+"\n\n"+eventsTrack.get(eventChoice).Adate+"\n\n"+eventsTrack.get(eventChoice).AsTime+" to "+eventsTrack.get(eventChoice).AeTime+"\n\n"+eventsTrack.get(eventChoice).D
        );
        textViewBlowup.setTextSize(20);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();
    }

    public void addEventsClick(View view){
        Events temp = new Events(eventsTrack.get(eventChoice).speaker, eventsTrack.get(eventChoice).STime, eventsTrack.get(eventChoice).ETime, eventsTrack.get(eventChoice).P, eventsTrack.get(eventChoice).D, eventsTrack.get(eventChoice).subject, eventsTrack.get(eventChoice).date, eventsTrack.get(eventChoice).tracks);
        Boolean check=true;
        for(int i=0; i<customizableList.size();i++){
            if(eventsEquals(customizableList.get(i), temp)){
                check=false;
            }
        }
        if(check){
            userClearCustomList=false;
            customizableList.add(temp);
            toast.show();

            for(int i=0; i<ratedName.size(); i++){
                if(temp.speaker.equals(ratedName.get(i)) && temp.date==ratedDate.get(i) && temp.STime==ratedSTime.get(i)){
                    countChild = childrenValues.get(i);
                    countCount = allCount.get(i);
                    countCount+=1;
                    allCount.set(i, countCount);
                }
            }
            DatabaseReference ref = database.getReference("events").child(countChild).child("count");
            ref.setValue(countCount);
            /*System.out.println("-----------------");
            DatabaseReference ref = database.getReference("events").child(countChild).child("count");
            ref.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    Count count = mutableData.getValue(Count.class);
                    count.count += 1;
                    mutableData.setValue(count);
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                }
            });*/

            for (int i = 0; i < customizableList.size(); i++) {
                editor.putInt(String.valueOf(i), findEvents(customizableList.get(i), list));
            }
            editor.putInt("customizableListSize", customizableList.size());
            editor.putBoolean("firstRun", false);
            editor.apply();
        }
        else{
            if(!(toast.getView().isShown())){
                toast=Toast.makeText(context, "This event has already been added.", duration);
                toast.show();
            }
        }
    }

    public static int convertTimes(int time){
        int finished;
        String sTime = String.valueOf(time);
        char[] aTime = sTime.toCharArray();
        List<Character> fTime = new ArrayList<>();
        for(int i=0; i<aTime.length; i++){
            fTime.add(aTime[i]);
        }
        if(aTime.length==3){
            finished=60*Integer.parseInt(String.valueOf(fTime.get(0)));
            fTime.remove(0);
            for(int i=0; i<fTime.size(); i++){
                if(i==0){
                    finished+=10*Integer.parseInt(String.valueOf(fTime.get(i)));
                }
                else{
                    finished+=Integer.parseInt(String.valueOf(fTime.get(i)));
                }
            }
        }
        else{
            finished=60*(10*(Integer.parseInt(String.valueOf(fTime.get(0))))+Integer.parseInt(String.valueOf(fTime.get(1))));
            fTime.remove(0);
            fTime.remove(0);
            for(int i=0; i<fTime.size(); i++){
                finished+=Integer.parseInt(String.valueOf(fTime.get(i)));
            }
        }

        return finished;
    }
}
