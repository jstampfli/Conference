package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.findEvents;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;

/**
 * Created by jstampfli19 on 11/8/17.
 */

public class PopTab2 extends Activity {
    TextView textViewBlowup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindowtab2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width =dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout(width,height);

        textViewBlowup = (TextView) findViewById(R.id.blowup);
        textViewBlowup.setText(
                "\n\n"+customizableList.get(popChoice).subject+"\n\n"+customizableList.get(popChoice).speaker+"\n\n"+customizableList.get(popChoice).P+"\n\n"+customizableList.get(popChoice).Adate+"\n\n"+customizableList.get(popChoice).AsTime+" to "+customizableList.get(popChoice).AeTime+"\n\n"+customizableList.get(popChoice).D
        );
        textViewBlowup.setTextSize(20);
    }
    public void removeEventsClick(View view){
        //Events temp = new Events(customizableList.get(popChoice).speaker, customizableList.get(popChoice).STime, customizableList.get(popChoice).ETime, customizableList.get(popChoice).P, customizableList.get(popChoice).D, customizableList.get(popChoice).subject, customizableList.get(popChoice).date);
        customizableList.remove(customizableList.get(popChoice));
        this.finish();

        for (int i = 0; i < customizableList.size(); i++) {
            editor.putInt(String.valueOf(i), findEvents(customizableList.get(i), list));
        }
        editor.putInt("customizableListSize", customizableList.size());
        editor.putBoolean("firstRun", false);
        editor.apply();
    }
}
