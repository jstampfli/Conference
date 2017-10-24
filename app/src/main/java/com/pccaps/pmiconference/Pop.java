package com.pccaps.pmiconference;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.Tab3.popChoice;

/**
 * Created by User1 on 10/3/2017.
 */

public class Pop extends Activity{

    TextView textViewBlowup;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

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
}
