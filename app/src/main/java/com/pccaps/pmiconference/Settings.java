package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.findEvents;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.clearCustomizableWarning.userClearCustomList;

/**
 * Created by jstampfli19 on 12/5/17.
 */

public class Settings extends Activity {

    Spinner alertArray;
    static int globalPosition=0;
    static String editorNotification = "notificationTime";

    static String selectedItem;
    static int selectedInt;
    static int state=prefs.getInt("notificationState", 0);

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

                selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Never"))
                {
                    state=0;
                    editor.putInt("notificationState", state);
                    editor.apply();
                }
                else if(selectedItem.equals("At Time of Event")){
                    state=1;
                    editor.putInt("notificationState", state);
                    editor.apply();
                }
                else{
                    state=2;
                    editor.putInt("notificationState", state);
                    editor.apply();
                    char[] temp = selectedItem.toCharArray();
                    List<Character> nums= new ArrayList<>();
                    String finalTemp="";
                    for(char e : temp){
                        if(e==' '){
                            break;
                        }
                        else{
                            nums.add(e);
                        }
                    }
                    for(char c : nums){
                        finalTemp.concat(String.valueOf(c));
                    }
                    selectedInt=Integer.parseInt(finalTemp);
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
