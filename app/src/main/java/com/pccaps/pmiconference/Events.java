package com.pccaps.pmiconference;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

    String AsTime;
    String AeTime;

    String Adate;

    Boolean am = false;
    Boolean pm = false;

    String spacer = "             ";
    String Lspacer = "                      ";
    String Sspacer = "   ";

    public Events(String name, long st, long et, String place, String Description, String topic, long day){
        speaker=name;
        STime=st;
        ETime=et;
        P=place;
        D=Description;
        subject=topic;
        date=day;

        AsTime = changeTime(st);
        AeTime = changeTime(et);

        Adate = changeDate(day);
    }

    public String toString(){
        return AsTime+spacer+speaker+"\n"+Sspacer+"to"+Lspacer+P+"\n"+AeTime+spacer+subject;
    }
    public String changeTime(long x){
        String time = String.valueOf(x);
        char[] eTime = time.toCharArray();
        List<Character> LeTime = new ArrayList<>();
        String whole="";
        String fHalf="";
        String eHalf="";
        int IfHalf;
        for(char i : eTime){
            LeTime.add(i);
        }
        for(int y=0; y<LeTime.size();y++){
            if(y+2<LeTime.size()){
                fHalf+=String.valueOf(LeTime.get(y));
            }
            else{
                eHalf+=String.valueOf(LeTime.get(y));
            }
        }
        for(char i : LeTime){
            whole+=String.valueOf(i);
        }
        int Iwhole = Integer.parseInt(whole);
        if(Iwhole<1300){
            am=true;
        }
        else{
            pm=true;
        }
        if(am){
            time=fHalf+":"+eHalf+" AM";
        }
        else{
            IfHalf = Integer.parseInt(fHalf);
            IfHalf = IfHalf%12;
            fHalf = String.valueOf(IfHalf);
            time=fHalf+":"+eHalf+" PM";
        }
        am=false;
        pm=false;
        return time;
    }

    public String changeDate(long x){
        String date = String.valueOf(x);
        char[] Cdate = date.toCharArray();

        String fHalf = "";
        String eHalf = "";

        List<Character> LCdate = new ArrayList<>();
        for(char i:Cdate){
            LCdate.add(i);
        }
        for(int y=0; y<LCdate.size();y++){
            if(y+2<LCdate.size()){
                fHalf+=String.valueOf(LCdate.get(y));
            }
            else{
                eHalf+=String.valueOf(LCdate.get(y));
            }
        }
        return fHalf+"/"+eHalf;
    }
}
