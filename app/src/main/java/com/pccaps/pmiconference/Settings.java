package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.findEvents;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.clearCustomizableWarning.userClearCustomList;

/**
 * Created by jstampfli19 on 12/5/17.
 */

public class Settings extends PreferenceActivity {

    Spinner alertArray;
    static int globalPosition=0;
    static String editorNotification = "notificationTime";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.settings);

        alertArray = (Spinner) findViewById(R.id.alertTimes);

        alertArray.setSelection(prefs.getInt(editorNotification, globalPosition), true);

        alertArray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                globalPosition=position;

                editor.putInt(editorNotification, globalPosition);
                editor.commit();

                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Never"))
                {

                }
                else if(selectedItem.equals("At Time of Event")){

                }
                else{

                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void clearCustomList(View view){

        startActivity(new Intent(getApplicationContext() ,clearCustomizableWarning.class));

        editor.putInt(editorNotification, globalPosition);
    }
}
