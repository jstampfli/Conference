package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Set;

import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.name;
import static com.pccaps.pmiconference.Tab3.popChoice;
import static com.pccaps.pmiconference.Tab3.startTime;

/**
 * Created by User1 on 10/3/2017.
 */

public class PopTab3 extends Activity{

    TextView textViewBlowup;

    Context context = this;
    String text = "Event Added";
    int duration = Toast.LENGTH_SHORT;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindowtab3);

        toast = Toast.makeText(context, text, duration);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width =dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout(width,height);

        textViewBlowup = (TextView) findViewById(R.id.blowup);
        textViewBlowup.setText(
                "\n\n"+list.get(popChoice).subject+"\n\n"+list.get(popChoice).speaker+"\n\n"+list.get(popChoice).P+"\n\n"+list.get(popChoice).Adate+"\n\n"+list.get(popChoice).AsTime+" to "+list.get(popChoice).AeTime+"\n\n"+list.get(popChoice).D
        );
        textViewBlowup.setTextSize(20);
    }

    public void addEventsClick(View view){
        Events temp = new Events(list.get(popChoice).speaker, list.get(popChoice).STime, list.get(popChoice).ETime, list.get(popChoice).P, list.get(popChoice).D, list.get(popChoice).subject, list.get(popChoice).date);
        Boolean check=true;
        for(int i=0; i<customizableList.size();i++){
            if((customizableList.get(i).getSTime()==temp.getSTime()) && (customizableList.get(i).subject.equals(temp.subject)) && (customizableList.get(i).speaker.equals(temp.speaker))){
                check=false;
            }
        }
        if(check){
            customizableList.add(temp);
            toast.show();
        }
        else{
            toast=Toast.makeText(context, "This event has already been added.", duration);
            toast.show();
        }
    }
}
