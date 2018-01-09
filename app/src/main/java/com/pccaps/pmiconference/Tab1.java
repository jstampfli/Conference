package com.pccaps.pmiconference;

/**
 * Created by User1 on 9/30/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.pccaps.pmiconference.Events.changeDate;
import static com.pccaps.pmiconference.MainActivity.toolbar;
import static com.pccaps.pmiconference.Tab2.customizableList;
import static com.pccaps.pmiconference.Tab2.editor;
import static com.pccaps.pmiconference.Tab2.prefs;
import static com.pccaps.pmiconference.Tab2.properDateList;
import static com.pccaps.pmiconference.Tab3.list;
import static com.pccaps.pmiconference.clearCustomizableWarning.userClearCustomList;

public class Tab1 extends Fragment{

    TextView pmi;
    TextView pmiLink;
    ImageView logo;
    Button settingsButton;
    Toolbar toolbar;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dRef = database.getReference("info");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        //MainActivity.toolbar.setTitle("About");

        //toolbar = (Toolbar) rootView.findViewById(R.id.toolbarAbout);


        pmi = (TextView) rootView.findViewById(R.id.pmiBackground);
        pmi.setTextSize(15);
        pmi.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        pmiLink = (TextView) rootView.findViewById(R.id.pmiLink);
        pmiLink.setTextSize(15);
        pmiLink.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        logo = (ImageView) rootView.findViewById(R.id.logo);

        settingsButton = (Button) rootView.findViewById(R.id.settings);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsPage = new Intent(getContext(), PreferenceActivitySettings.class);
                startActivity(settingsPage);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(userClearCustomList){
            for (int i = 0; i < prefs.getInt("customizableListSize", 0); i++) {
                editor.remove(String.valueOf(i));
            }
            customizableList.clear();
            editor.putInt("customizableListSize", 0);
            editor.commit();
        }

        pmi.setText("");
        List<String> datesRange = new ArrayList<>();
        for(Events e:list){
            datesRange.add(changeDate(e.date));
        }
        pmi.setText(datesRange.get(0)+" - "+datesRange.get(datesRange.size()-1));
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(final DataSnapshot snapshot : dataSnapshot.getChildren() ){
                    /*if(i==0){
                        i++;
                        continue;
                    }*/
                    if(snapshot.getKey().equals("website")){
                        pmiLink.setText(String.valueOf(snapshot.getValue()));
                    }
                    else{
                        pmi.setText(pmi.getText()+String.valueOf(snapshot.getValue())+"\n\n");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        pmi.setText(pmi.getText()+"\n\nCreated By Jordan Stampfli\n\n");
    }
}
