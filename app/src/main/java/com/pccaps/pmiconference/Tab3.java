package com.pccaps.pmiconference;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User1 on 9/30/2017.
 */

public class Tab3 extends Fragment {

    ListView listView;
    List list = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_view);

        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Berry");
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Berry");
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Berry");
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Berry");
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Berry");
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Berry");

        listView.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, list));

        return rootView;

    }

}
