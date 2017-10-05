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
    static Events jim = new Events("jim", 3, 5, "Library", "asdf");
    static Events j = new Events("jim", 3, 5, "Library", "asdc");
    static Events ji = new Events("jim", 3, 5, "Library", "asdfsadf");
    static Events im = new Events("jim", 3, 5, "Library", "sdfs");

    ListView listView;
    static List<Events> list = new ArrayList<>(
        Arrays.asList(jim, j, ji, im)
    );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_view);
        ArrayAdapter<Events> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, list);
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
