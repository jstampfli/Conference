package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

//import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.PopTabCustomizable.adapter;
import static com.pccaps.pmiconference.PopTabCustomizable.customizableChoice;
import static com.pccaps.pmiconference.PopTabCustomizable.dateChoice;
import static com.pccaps.pmiconference.PopTabCustomizable.dateCustomizableList;
import static com.pccaps.pmiconference.PopTabCustomizable.listView;
import static com.pccaps.pmiconference.Tab2.customizableDates;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.eventsEquals;
import static com.pccaps.pmiconference.Tab2.findEvents;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab2.tab2Choice;
import static com.pccaps.pmiconference.Tab3.allCount;
import static com.pccaps.pmiconference.Tab3.childrenValues;
import static com.pccaps.pmiconference.Tab3.countCount;
import static com.pccaps.pmiconference.Tab3.database;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.Tab3.ratedDate;
import static com.pccaps.pmiconference.Tab3.ratedName;
import static com.pccaps.pmiconference.Tab3.ratedSTime;

/**
 * Created by jstampfli19 on 11/8/17.
 */

public class PopTab2 extends AppCompatActivity {
    TextView textViewBlowup;
    TextView descriptionLink;

    static String removeCountChild;
    static Boolean kill=false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindowtab2);

        getSupportActionBar().setTitle("Details");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width =dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout(width,height);

        descriptionLink = (TextView) findViewById(R.id.description);
        descriptionLink.setText(customizableList.get(customizableChoice).D);
        descriptionLink.setTextSize(20);

        textViewBlowup = (TextView) findViewById(R.id.blowup);
        textViewBlowup.setText(
                "\n\n"+customizableList.get(customizableChoice).subject+"\n\n"+customizableList.get(customizableChoice).speaker+"\n\n"+customizableList.get(customizableChoice).P+"\n\n"+customizableList.get(customizableChoice).Adate+"\n\n"+customizableList.get(customizableChoice).AsTime+" to "+customizableList.get(customizableChoice).AeTime+"\n\n"
        );
        textViewBlowup.setTextSize(20);
    }
    public void removeEventsClick(View view){
        Events temp = new Events(customizableList.get(customizableChoice).speaker, customizableList.get(customizableChoice).STime, customizableList.get(customizableChoice).ETime, customizableList.get(customizableChoice).P, customizableList.get(customizableChoice).D, customizableList.get(customizableChoice).subject, customizableList.get(customizableChoice).date, customizableList.get(customizableChoice).tracks);
        Boolean otherDate=false;
        for(Events e:customizableList){
            if(e.date==temp.date && !eventsEquals(e, temp)){
                otherDate=true;
            }
        }
        if(!otherDate){
            for(int i=0; i<customizableDates.size(); i++){
                if(customizableDates.get(i).equals(changeDate(temp.date))){
                    customizableDates.remove(i);
                }
                if(prefs.getString("c"+String.valueOf(i), "").equals(changeDate(temp.date))){
                    editor.remove("c"+String.valueOf(i));
                    editor.putInt("datesSize", prefs.getInt("datesSize", 0)-1);
                }
            }
        }
        for (int i = 0; i < customizableList.size(); i++) {
            editor.remove(String.valueOf(i));
        }

        for(int i=0; i<ratedName.size(); i++){
            if(customizableList.get(customizableChoice).speaker.equals(ratedName.get(i)) && customizableList.get(customizableChoice).date==ratedDate.get(i) && customizableList.get(customizableChoice).STime==ratedSTime.get(i)){
                removeCountChild = childrenValues.get(i);
                countCount = allCount.get(i);
                countCount-=1;
                allCount.set(i,countCount);
            }
        }
        DatabaseReference ref = database.getReference("events").child(removeCountChild).child("count");
        ref.setValue(countCount);

        customizableList.remove(customizableList.get(customizableChoice));
        dateCustomizableList.remove(dateChoice);
        if(dateCustomizableList.size()==0){
            kill=true;
        }
        listView.setAdapter(adapter);

        /**/

        for (int i = 0; i < customizableList.size(); i++) {
            editor.putInt(String.valueOf(i), findEvents(customizableList.get(i), list));
        }
        editor.putInt("customizableListSize", customizableList.size());
        editor.putBoolean("firstRun", false);
        editor.apply();

        this.finish();
    }
}
