package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;

/**
 * Created by User1 on 10/3/2017.
 */

public class PopTab3 extends Activity{

    TextView textViewBlowup;

    /*Context context = getApplicationContext();
    CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;

    Toast toast = Toast.makeText(context, text, duration);
    toast.show();*/

    Context context = this;
    String text = "Adding Event";
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
        customizableList.add(temp);
        toast.show();
    }
}
