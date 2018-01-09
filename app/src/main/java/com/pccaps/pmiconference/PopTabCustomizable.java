package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.R.layout.popupwindowdates;
import static com.pccaps.pmiconference.R.layout.popupwindowevents;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.howToAdd;
import static com.pccaps.pmiconference.Tab2.intro;
import static com.pccaps.pmiconference.Tab2.tab2Choice;
import static com.pccaps.pmiconference.Tab3.dateList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.Tab3.trackList;
import static com.pccaps.pmiconference.clearCustomizableWarning.userClearCustomList;

/**
 * Created by jstampfli19 on 11/14/17.
 */

public class PopTabCustomizable extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<Events> adapter;

    List<Events> dateCustomizableList = new ArrayList<>();

    static int customizableChoice;

    TextView helper;
    TextView helperIntro;
    Toolbar toolbarCustomizable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(popupwindowdates);

        //toolbarCustomizable = (Toolbar) findViewById(R.id.toolbarCustomizable);
        //Might need to create a toolbar in the res file for each popup class and use those for each individual popup class
        getSupportActionBar().setTitle("Event Tab");
        //setSupportActionBar(toolbar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width,height);

        listView = (ListView) findViewById(R.id.listDates);

        if(customizableList.size()>0){
            for(Events e : customizableList){
                if(e.date==dateList.get(tab2Choice)){
                    dateCustomizableList.add(e);
                }
            }
        }

        helper = (TextView)findViewById(R.id.helperText);
        helperIntro = (TextView) findViewById(R.id.hIntro);

        if(dateCustomizableList.size()!=0){
            helper.setText("");
            helperIntro.setText("");
        }
        else{
            helper.setText(howToAdd);
            helperIntro.setText(intro);
        }

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.activity_list_item, android.R.id.text1, dateCustomizableList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customizableChoice=customizableList.indexOf(dateCustomizableList.get(position));
                startActivity(new Intent(getApplicationContext(), PopTab2.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
