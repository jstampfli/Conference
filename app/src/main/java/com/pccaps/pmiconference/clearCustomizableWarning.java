package com.pccaps.pmiconference;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import static com.pccaps.pmiconference.Settings.editorNotification;
import static com.pccaps.pmiconference.Settings.globalPosition;
import static com.pccaps.pmiconference.Tab2.customizableDates;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab3.allCount;
import static com.pccaps.pmiconference.Tab3.childrenValues;
import static com.pccaps.pmiconference.Tab3.countCount;
import static com.pccaps.pmiconference.Tab3.database;
import static com.pccaps.pmiconference.Tab3.ratedDate;
import static com.pccaps.pmiconference.Tab3.ratedName;
import static com.pccaps.pmiconference.Tab3.ratedSTime;

/**
 * Created by jstampfli19 on 12/7/17.
 */

public class clearCustomizableWarning extends Activity {
    TextView textViewBlowup;
    static boolean userClearCustomList=false;

    //Context context = Settings.class.get;
    String text = "List Cleared";
    int duration = Toast.LENGTH_SHORT;

    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.clear_warning);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = 1000;
        int height = 600;

        getWindow().setLayout(width, height);
    }

    public void clearClick(View view){
        userClearCustomList=true;
        String countChild="";
        for(Events e: customizableList){
            for(int i=0; i<ratedName.size(); i++){
                if(e.speaker.equals(ratedName.get(i)) && e.date==ratedDate.get(i) && e.STime==ratedSTime.get(i)){
                    countChild = childrenValues.get(i);
                    countCount = allCount.get(i);
                    countCount-=1;
                    allCount.set(i, countCount);
                }
            }
            DatabaseReference ref = database.getReference("events").child(countChild).child("count");
            ref.setValue(countCount);
        }
        customizableDates.clear();
        this.finish();
    }
    public void saveClick(View view){
        userClearCustomList=false;
        this.finish();
    }

}
