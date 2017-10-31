package com.pccaps.pmiconference;

/**
 * Created by User1 on 9/30/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab1 extends Fragment{

    TextView pmi;
    ImageView logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        pmi = (TextView) rootView.findViewById(R.id.pmiBackground);
        pmi.setText("2018 Professional Development Conference\n\nJune 1 - June 4\n\nSLC Convention Center\n\nwww.projectmanager.org\n\n(123) 456-7890");
        pmi.setTextSize(15);
        pmi.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        logo = (ImageView) rootView.findViewById(R.id.logo);

        return rootView;
    }
}
