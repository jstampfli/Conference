package com.pccaps.pmiconference;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pccaps.pmiconference.Tab3.popChoice;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab2 extends Fragment {

    static ListView eventsView;
    static List<Events> customizableList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        eventsView = (ListView) rootView.findViewById(R.id.eventsView);

        eventsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popChoice=position;
                startActivity(new Intent(getActivity(), PopTab2.class));
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, customizableList);
        eventsView.setAdapter(adapter);

        Collections.sort(customizableList, new Tab3.CompareEvents());
    }
}
