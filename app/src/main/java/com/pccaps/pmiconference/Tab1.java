package com.pccaps.pmiconference;

/**
 * Created by User1 on 9/30/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tab1 extends Fragment{

    TextView pmi;
    ImageView logo;
    Button settingsButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef = database.getReference("info");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        pmi = (TextView) rootView.findViewById(R.id.pmiBackground);
        pmi.setTextSize(15);
        pmi.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        logo = (ImageView) rootView.findViewById(R.id.logo);

        settingsButton = (Button) rootView.findViewById(R.id.settings);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsPage = new Intent(getContext(), Settings.class);
                startActivity(settingsPage);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pmi.setText("");
                int i=0;
                for(final DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    if(i==0){
                        i++;
                        continue;
                    }
                    pmi.setText(pmi.getText()+String.valueOf(snapshot.getValue())+"\n\n");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
