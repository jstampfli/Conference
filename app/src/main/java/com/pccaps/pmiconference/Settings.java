package com.pccaps.pmiconference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;

/**
 * Created by jstampfli19 on 12/5/17.
 */

public class Settings extends Activity {

    Spinner alertArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.settings);

        alertArray = (Spinner) findViewById(R.id.alertTimes);

        alertArray.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
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

        customizableList.clear();
        editor.clear();
        editor.commit();
    }

    public void addListenerOnSpinnerItemSelection(){

    }
}
