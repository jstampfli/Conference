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

import static com.pccaps.pmiconference.Settings.editorNotification;
import static com.pccaps.pmiconference.Settings.globalPosition;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;

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

        if(userClearCustomList){
            for (int i = 0; i < customizableList.size(); i++) {
                editor.remove(String.valueOf(i));
            }
            customizableList.clear();
            //editor.clear();
            //editor.putInt(editorNotification, globalPosition);
            editor.apply();
        }
    }

    public void clearClick(View view){
        userClearCustomList=true;
        /*for (int i = 0; i < customizableList.size(); i++) {
            editor.remove(String.valueOf(i));
        }
        customizableList.clear();
        //editor.clear();
        //editor.putInt(editorNotification, globalPosition);
        editor.commit();*/
        this.finish();
    }
    public void saveClick(View view){
        userClearCustomList=false;
        this.finish();
    }

}
