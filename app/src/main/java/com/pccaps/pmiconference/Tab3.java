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

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab3 extends Fragment{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef = database.getReference("events");

    static String name;
    static long startTime;
    static long endTime;
    static String description;
    static long date;
    static String location;
    static String subject;
    static String tracks;

    static int countClear=0;

    static Object[] data = new Object[8];

    static int dataTemp=0;

    static int popChoice;
    static Events temp;

    ListView listView;
    static List<Events> list = new ArrayList<>();

    static List<String> trackList = new ArrayList<>();

    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popChoice=position;
                startActivity(new Intent(getActivity(), PopTabEvents.class));
            }
        });

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        //trackList.clear();

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();
                trackList.clear();

                for(final DataSnapshot snapshot : dataSnapshot.getChildren() ){

                    snapshot.getRef().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if(String.valueOf(snapshot.getRef()).equals("https://pmiconferencedatabase.firebaseio.com/events/event1")){
                                list.clear();
                            }

                            for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                                Object retrieve = snapshot.getValue();
                                data[dataTemp]=retrieve;
                                dataTemp++;
                            }
                            dataTemp=0;
                            name = (String) data[3];
                            startTime = (long) data[6];
                            endTime = (long) data[5];
                            description = (String) data[1];
                            date = (long) data[0];
                            location = (String) data[2];
                            subject = (String) data[4];
                            tracks = (String) data[7];

                            if(!trackList.contains(tracks)){
                                trackList.add(tracks);
                            }

                            Collections.sort(trackList);

                            temp = new Events(name, startTime, endTime, location, description, subject, date, tracks);
                            list.add(temp);

                            Collections.sort(list, new CompareEvents());

                            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, trackList);
                            listView.setAdapter(adapter);

                            countClear++;
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

