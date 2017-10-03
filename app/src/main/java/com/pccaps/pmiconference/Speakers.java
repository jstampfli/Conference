package com.pccaps.pmiconference;

/**
 * Created by User1 on 10/3/2017.
 */

public class Speakers {
    String speaker;
    int STime;
    int ETime;
    String P;
    String D;

    public Speakers(String name, int st, int et, String place, String Description){
        speaker=name;
        STime=st;
        ETime=et;
        P=place;
        D=Description;
    }
    public String toString(){
        return speaker+"\n"+String.valueOf(STime)+" to "+String.valueOf(ETime)+"\n"+P+"\n"+D;
    }
}
