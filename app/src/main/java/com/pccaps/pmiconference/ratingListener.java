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

import static com.pccaps.pmiconference.Tab2.ratedEvent;
import static com.pccaps.pmiconference.Tab3.childrenValues;
import static com.pccaps.pmiconference.Tab3.data;
import static com.pccaps.pmiconference.Tab3.dataTemp;
import static com.pccaps.pmiconference.Tab3.date;
import static com.pccaps.pmiconference.Tab3.name;
import static com.pccaps.pmiconference.Tab3.ratedDate;
import static com.pccaps.pmiconference.Tab3.ratedName;
import static com.pccaps.pmiconference.Tab3.ratedSTime;
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

    @Override
    public void onReceive(Context context, Intent intent) {

        databaseReference= FirebaseDatabase.getInstance().getReference();

        NotificationManager manager = (NotificationManager) context. getSystemService(context. NOTIFICATION_SERVICE);

        for(int i=0; i<ratedName.size(); i++){
            if(ratedEvent.speaker.equals(ratedName.get(i)) && ratedEvent.date==ratedDate.get(i) && ratedEvent.STime==ratedSTime.get(i)){
                eventChild=childrenValues.get(i);
            }
        }

        if(intent.getIntExtra("id", 0)==385921){
            databaseReference.child("events").child(eventChild).child("ratings").push().setValue(1);
        }
        else if(intent.getIntExtra("id", 0)==385922){
            databaseReference.child("events").child(eventChild).child("ratings").push().setValue(2);
        }
        else if(intent.getIntExtra("id", 0)==385923){
            databaseReference.child("events").child(eventChild).child("ratings").push().setValue(3);
        }
        else if(intent.getIntExtra("id", 0)==385924){
            databaseReference.child("events").child(eventChild).child("ratings").push().setValue(4);
        }
        else {
            databaseReference.child("events").child(eventChild).child("ratings").push().setValue(5);
        }

        Toast.makeText(context, "Thank You for Your Rating", Toast.LENGTH_SHORT).show();
        manager.cancelAll();
    }

}
