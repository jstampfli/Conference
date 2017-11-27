package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.SharedPreferences;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.Tab3.dateList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.PopTabEvents.eventChoice;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab2 extends Fragment {

    static ListView eventsView;
    static List<Events> customizableList = new ArrayList<>();
    static ArrayAdapter datesAdapter;

    static String intro = "To add and Event to this Page:";
    static String howToAdd = "\n1.\tGo to the schedule tab\n\n 2.\tClick on a track and then an event that you find interesting\n\n 3.\tClick the \"Add Event\" button\n\n4.\tGo to the \"SAVED EVENTS\" tab to see the event";

    static SharedPreferences prefs;
    static SharedPreferences.Editor editor;

    static List<String> properDateList = new ArrayList<>();

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
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        eventsView = (ListView) rootView.findViewById(R.id.eventsView);

        eventsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popChoice=position;
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

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        properDateList.clear();

        for(long i:dateList){
            properDateList.add(changeDate(i));
        }

        Collections.sort(properDateList);

        datesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, properDateList);
        eventsView.setAdapter(datesAdapter);

        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = prefs.edit();


        /*if(!prefs.getBoolean("firstRun", true)) {
            for (int i = 0; i < customizableList.size(); i++) {
                editor.putInt(String.valueOf(i), findEvents(customizableList.get(i)));
            }
            editor.putInt("customizableListSize", customizableList.size());
            editor.putBoolean("firstRun", false);
            editor.apply();
        }*/

        Collections.sort(customizableList, new Tab3.CompareEvents());
    }
}
