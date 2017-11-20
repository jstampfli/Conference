package com.pccaps.pmiconference;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
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
    String tracks;

    String AsTime;
    String AeTime;

    String Adate;

    Boolean am = false;
    Boolean pm = false;

    /*String spacer = "  ";
    String Lspacer = "                      ";
    String Sspacer = "   ";*/

    String[] months = new String[]{
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "Novermber",
            "December"
    };

    public Events(String name, long st, long et, String place, String Description, String topic, long day, String t){

        speaker=name;
        STime=st;
        ETime=et;
        P=place;
        D=Description;
        subject=topic;
        date=day;
        tracks=t;

        AsTime = changeTime(st);
        AeTime = changeTime(et);

        Adate = changeDate(day);
    }

    /*public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }*/

    public String evenSpace(String x){
        char[] num = x.toCharArray();
        String space=" ";
        for(int i=num.length+1; i<11; i++){
            space=space+"  ";
        }
        x+=space;
        return x;
    }

    public String toString(){
        //String rightSide = String.format("%4s",speaker+"\n"+P+"\n"+subject);
        //String leftSide = AsTime+"\nto\n"+AeTime;
        return evenSpace(AsTime)+subject+"\n"+evenSpace("to")+"  "+P+"\n"+evenSpace(AeTime)+speaker;
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
        char[] cEHalf = eHalf.toCharArray();
        if(cEHalf[0]=='0'){
            return months[Integer.parseInt(fHalf)-1]+" "+String.valueOf(cEHalf[1]);
        }
        else{
            return months[Integer.parseInt(fHalf)-1]+" "+eHalf;
        }
    }
    public int getSTime(){
        return (int)STime;
    }
    public int getDate(){
        return (int)date;
    }
}
