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
import static com.pccaps.pmiconference.Tab2.prefs;

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
        this.finish();
    }
    public void saveClick(View view){
        userClearCustomList=false;
        this.finish();
    }

}
