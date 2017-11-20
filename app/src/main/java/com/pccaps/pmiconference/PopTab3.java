package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Set;

import com.google.gson.Gson;

import org.json.JSONArray;

import static com.pccaps.pmiconference.PopTabEvents.eventsTrack;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.eventsEquals;
import static com.pccaps.pmiconference.Tab2.findEvents;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.name;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.PopTabEvents.eventChoice;
import static com.pccaps.pmiconference.Tab3.startTime;

/**
 * Created by User1 on 10/3/2017.
 */

public class PopTab3 extends Activity{

    //comment created so i could submit unversioned files
    TextView textViewBlowup;

    Context context = this;
    String text = "Event Added";
    int duration = Toast.LENGTH_SHORT;

    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindowtab3);

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
            customizableList.add(temp);
            toast.show();

            for (int i = 0; i < customizableList.size(); i++) {
                editor.putInt(String.valueOf(i), findEvents(customizableList.get(i), list));
            }
            editor.putInt("customizableListSize", customizableList.size());
            editor.putBoolean("firstRun", false);
            editor.apply();

            //editor.clear();
            //editor.commit();
        }
        else{
            if(!(toast.getView().isShown())){
                toast=Toast.makeText(context, "This event has already been added.", duration);
                toast.show();
            }
        }
    }
}
