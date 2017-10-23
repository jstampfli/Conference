package com.pccaps.pmiconference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User1 on 10/3/2017.
 */


public class Events {
    String speaker;
    static long STime;
    static long ETime;
    String P;
    String D;
    String subject;
    long date;
    static Boolean am = false;
    static Boolean pm = false;

    String spacer = "            ";
    String Lspacer = "               ";
    String Sspacer = "   ";

    static String AsTime = changeTime(STime);
    static String AeTime = changeTime(ETime);

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
        return AsTime+spacer+speaker+"\n"+Sspacer+"to"+Lspacer+P+"\n"+AeTime+spacer+subject;
    }
    public static String changeTime(long x){
        String time = String.valueOf(x);
        char[] eTime = time.toCharArray();
        List<Character> LeTime = new ArrayList<>();
        String whole="";
        String fHalf="";
        String eHalf="";
        for(char i : eTime){
            LeTime.add(i);
        }
        for(char i : LeTime){
            if(LeTime.indexOf(i)+3<LeTime.size()){
                fHalf+=String.valueOf(i);
            }
            else{
                eHalf+=String.valueOf(i);
            }
        }
        for(char i : eTime){
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
            time=fHalf+":"+eHalf+" PM";
        }
        return time;
    }
}
