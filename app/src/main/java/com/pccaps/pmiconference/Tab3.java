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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab3 extends Fragment {

    static int popChoice;
<<<<<<< HEAD
    static Event jim = new Event("Jim", 3, 5, "Library", "PMI", "PMI", 1008);
    static Event j = new Event("Jim", 3, 5, "Library", "PMI", "PMI", 1009);
    static Event ji = new Event("Jim", 3, 5, "Library", "PMI", "PMI", 1018);
    static Event im = new Event("Jim", 3, 5, "Library", "PMI", "PMI", 1098);
    static Event m = new Event("Jim", 3, 5, "Library", "PMI", "PMI", 1098);
    static Event i = new Event("Jim", 3, 5, "Library", "PMI", "PMI", 1098);

    ListView listView;
    static List<Event> list = new ArrayList<>(
        Arrays.asList(jim, j, ji, im, m, i)
=======
    static Events jim = new Events("jim", 3, 5, "Library", "asdf");
    static Events j = new Events("jim", 3, 5, "Library", "asdc");
    static Events ji = new Events("jim", 3, 5, "Library", "asdfsadf");
    static Events im = new Events("jim", 3, 5, "Library", "sdfs");

    ListView listView;
    static List<Events> list = new ArrayList<>(
        Arrays.asList(jim, j, ji, im)
>>>>>>> 6b47ae8283a8ffb7774146bf266de8141c556fe8
    );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_view);
<<<<<<< HEAD
        ArrayAdapter<Event> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, list);
=======
        ArrayAdapter<Events> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, list);
>>>>>>> 6b47ae8283a8ffb7774146bf266de8141c556fe8
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popChoice=position;
                startActivity(new Intent(getActivity(), Pop.class));
            }
        });

        return rootView;

    }
    public static void main(String[] args){

    }


}
