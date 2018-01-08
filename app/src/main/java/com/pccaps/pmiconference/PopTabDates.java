package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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

public class PopTabDates extends Activity {

    ListView listView;
    ArrayAdapter<String> adapter;

    static List<Events> datesTrack = new ArrayList<>();

    static int eventDateChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popupwindowdates);

        datesTrack.clear();

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

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.activity_list_item, android.R.id.text1, properDateList);
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
