package com.pccaps.pmiconference;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

    Speakers jim = new Speakers("jim", 3, 5, "Library", "asdf");
    Speakers j = new Speakers("jim", 3, 5, "Library", "asdc");
    Speakers ji = new Speakers("jim", 3, 5, "Library", "asdfsadf");
    Speakers im = new Speakers("jim", 3, 5, "Library", "sdfs");

    ListView listView;
    List<Speakers> list = new ArrayList<>(
        Arrays.asList(jim, j, ji, im)
    );


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_view);
        ArrayAdapter<Speakers> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.activity_list_item, android.R.id.text1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return rootView;

    }
    public static void main(String[] args){

    }


}
