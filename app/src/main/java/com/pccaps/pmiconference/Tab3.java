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
import java.util.Arrays;
import java.util.List;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab3 extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef = database.getReference("events");
    DatabaseReference dRefEventNum = dRef.child("event1");

    static String name;
    static long startTime;
    static long endTime;
    static String description;
    static long date;
    static String location;
    static String subject;

    static Object[] data = new Object[7];

    static int dataTemp=0;

    static int popChoice;
    static Events jim = new Events("jim", 3, 5, "Library", "asdf", "", 5);
    static Events j = new Events("jim", 3, 5, "Library", "asdc", "", 5);
    static Events ji = new Events("jim", 3, 5, "Library", "asdfsadf", "", 5);
    static Events im = new Events("jim", 3, 5, "Library", "sdfs", "", 5);
    static Events temp;

    ListView listView;
    static List<Events> list = new ArrayList<>(
        Arrays.asList(jim, j, ji, im)
    );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //dRef=FirebaseDatabase.getInstance().getReference("Events");

        View rootView = inflater.inflate(R.layout.tab3, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_view);
        //ArrayAdapter<Events> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, list);
        //listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popChoice=position;
                startActivity(new Intent(getActivity(), Pop.class));
            }
        });

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    snapshot.getRef().addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                                Object retrieve = snapshot.getValue();
                                data[dataTemp]=retrieve;
                                dataTemp++;
                                for(int i=0; i<data.length; i++){
                                    System.out.println(String.valueOf(data[i]));
                                }
                            }
                            dataTemp=0;
                            name = (String) data[3];
                            startTime = (long) data[6];
                            endTime = (long) data[5];
                            description = (String) data[1];
                            date = (long) data[0];
                            location = (String) data[2];
                            subject = (String) data[4];

                            temp = new Events(name, startTime, endTime, location, description, subject, date);
                            list.add(temp);
                            ArrayAdapter<Events> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, list);
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


}
