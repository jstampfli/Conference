package com.pccaps.pmiconference;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.Tab2.datesAdapter;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.eventsView;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab2.properDateList;
import static com.pccaps.pmiconference.Tab2.ratedEvent;

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

    static int dataTemp=0;

    static int popChoice;
    static Events temp;

    ListView listView;
    static List<Events> list = new ArrayList<>();

    static List<String> trackList = new ArrayList<>();

    static List<Long> dateList = new ArrayList<>();

    ArrayAdapter<String> adapter;

    static List<Integer> allRatings = new ArrayList<>();

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

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();

        datesAdapter = new ArrayAdapter<>(getActivity(), R.layout.custom_list, android.R.id.text1, properDateList);
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

                            datesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, properDateList);
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

