package com.pccaps.pmiconference;

/**
 * Created by User1 on 10/3/2017.
 */


public class Events {
    String speaker;
    long STime;
    long ETime;
    String P;
    String D;
    String subject;
    long date;

    public Events(String name, long st, long et, String place, String Description, String topic, long day){
        speaker=name;
        STime=st;
        ETime=et;
        P=place;
        D=Description;
        subject=topic;
        date=day;
    }
    public String toString(){
        return speaker+"\n"+String.valueOf(STime)+" to "+String.valueOf(ETime)+"\n"+P+"\n"+D+"\n"+subject+"\n"+String.valueOf(date);
    }
}
