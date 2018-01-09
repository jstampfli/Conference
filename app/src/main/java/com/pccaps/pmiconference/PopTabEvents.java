package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.PopTabDates.eventDateChoice;
import static com.pccaps.pmiconference.R.layout.popupwindowevents;
import static com.pccaps.pmiconference.Tab3.date;
import static com.pccaps.pmiconference.Tab3.dateList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.Tab3.trackList;

/**
 * Created by jstampfli19 on 11/14/17.
 */

public class PopTabEvents extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<Events> adapter;

    static List<Events> eventsTrack = new ArrayList<>();
    static List<Events> tempTrack = new ArrayList<>();

    static int eventChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popupwindowevents);

        eventsTrack.clear();
        tempTrack.clear();

        toolbar.setTitle("Events");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width,height);

        listView = (ListView) findViewById(R.id.listEvents);

        for(int i=0; i<list.size(); i++){
            if(list.get(i).tracks.equals(trackList.get(popChoice))){
                tempTrack.add(list.get(i));
            }
        }
        for(int i=0; i<tempTrack.size(); i++){
            if(tempTrack.get(i).date==dateList.get(eventDateChoice)){
                eventsTrack.add(tempTrack.get(i));
            }
        }

        adapter = new ArrayAdapter<Events>(getApplicationContext(), android.R.layout.activity_list_item, android.R.id.text1, eventsTrack);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eventChoice=position;
                startActivity(new Intent(getApplicationContext(), PopTab3.class));
            }
        });

    }


}
