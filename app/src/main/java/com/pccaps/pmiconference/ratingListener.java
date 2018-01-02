package com.pccaps.pmiconference;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab2.ratedEvent;
import static com.pccaps.pmiconference.Tab3.allRatings;
import static com.pccaps.pmiconference.Tab3.childrenValues;
import static com.pccaps.pmiconference.Tab3.data;
import static com.pccaps.pmiconference.Tab3.dataTemp;
import static com.pccaps.pmiconference.Tab3.date;
import static com.pccaps.pmiconference.Tab3.name;
import static com.pccaps.pmiconference.Tab3.ratedDate;
import static com.pccaps.pmiconference.Tab3.ratedName;
import static com.pccaps.pmiconference.Tab3.ratedSTime;
import static com.pccaps.pmiconference.Tab3.ratingsCount;
import static com.pccaps.pmiconference.Tab3.startTime;
import static com.pccaps.pmiconference.Tab3.endTime;
import static com.pccaps.pmiconference.Tab3.amountPicked;
import static com.pccaps.pmiconference.Tab3.description;
import static com.pccaps.pmiconference.Tab3.location;
import static com.pccaps.pmiconference.Tab3.subject;
import static com.pccaps.pmiconference.Tab3.tracks;

/**
 * Created by jstampfli19 on 1/1/18.
 */

public class ratingListener extends BroadcastReceiver {

    private DatabaseReference databaseReference;

    static String eventChild;
    static int ratingValue;

    static rating rating;

    @Override
    public void onReceive(Context context, Intent intent) {

        databaseReference= FirebaseDatabase.getInstance().getReference();

        NotificationManager manager = (NotificationManager) context. getSystemService(context. NOTIFICATION_SERVICE);

        for(int i=0; i<ratedName.size(); i++){
            if(ratedEvent.speaker.equals(ratedName.get(i)) && ratedEvent.date==ratedDate.get(i) && ratedEvent.STime==ratedSTime.get(i)){
                eventChild=childrenValues.get(i);
                ratingValue=allRatings.get(i);
            }
        }

        if(intent.getIntExtra("id", 0)==385921){
            rating = new rating("", 1);
            databaseReference.child("events").child(eventChild).child("ratings").child("rating"+String.valueOf(ratingValue)).setValue(rating);
            ratingsCount++;
            //databaseReference.child("events").child(eventChild).child("ratings").setValue("rating"+String.valueOf(ratingsCount+1));
        }
        else if(intent.getIntExtra("id", 0)==385922){
            rating = new rating("", 2);
            databaseReference.child("events").child(eventChild).child("ratings").child("rating"+String.valueOf(ratingValue)).setValue(rating);
            ratingsCount++;
        }
        else if(intent.getIntExtra("id", 0)==385923){
            rating = new rating("", 3);
            databaseReference.child("events").child(eventChild).child("ratings").child("rating"+String.valueOf(ratingValue)).setValue(rating);
            ratingsCount++;
        }
        else if(intent.getIntExtra("id", 0)==385924){
            rating = new rating("", 4);
            databaseReference.child("events").child(eventChild).child("ratings").child("rating"+String.valueOf(ratingValue)).setValue(rating);
            ratingsCount++;
        }
        else {
            rating = new rating("", 5);
            databaseReference.child("events").child(eventChild).child("ratings").child("rating"+String.valueOf(ratingValue)).setValue(rating);
            ratingsCount++;
        }

        editor.putInt("ratingNum", ratingsCount);
        editor.apply();
        Toast.makeText(context, "Thank You for Your Rating", Toast.LENGTH_SHORT).show();
        manager.cancelAll();
    }

}
