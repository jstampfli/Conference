package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.R.layout.popupwindowdates;
import static com.pccaps.pmiconference.R.layout.popupwindowevents;
import static com.pccaps.pmiconference.Tab2.properDateList;
import static com.pccaps.pmiconference.Tab3.dateList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.Tab3.trackList;

/**
 * Created by jstampfli19 on 11/14/17.
 */

public class PopTabDates extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;

    static List<String> datesTrack = new ArrayList<>();

    static int eventDateChoice;

    Toolbar toolbarDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popupwindowdates);

        datesTrack.clear();

        /*toolbarDates = (Toolbar) findViewById(R.id.toolbarCustomizable);
        toolbarDates.setTitle("Event Dates");
        setSupportActionBar(toolbarDates);*/

        getSupportActionBar().setTitle(trackList.get(popChoice));
        //setSupportActionBar(toolbar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width,height);

        listView = (ListView) findViewById(R.id.listDates);

        /*for(int i=0; i<list.size(); i++){
            if(list.get(i).date==dateList.get(popChoice)){
                datesTrack.add(list.get(i));
            }
        }*/
        for(long i:dateList){
            if(list.size()!=0){
                for(Events e:list){
                    if(e.tracks.equals(trackList.get(popChoice))){
                        if(e.date==i){
                            if(!datesTrack.contains(changeDate(i))){
                                datesTrack.add(changeDate(i));
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(datesTrack);

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.activity_list_item, android.R.id.text1, datesTrack);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eventDateChoice=position;
                startActivity(new Intent(getApplicationContext(), PopTabEvents.class));
            }
        });
    }


}

