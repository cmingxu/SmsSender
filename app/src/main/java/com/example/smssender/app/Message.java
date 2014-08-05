package com.example.smssender.app;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by xcm on 8/5/14.
 */
public class Message {
    public String message_content;
    public String recepents;

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getRecepents() {
        return recepents;
    }

    public void setRecepents(String recepents) {
        this.recepents = recepents;
    }

    public ArrayList<String> getRecepentsInArray(){
        ArrayList<String> recepents = new ArrayList<String>();
        for(String s : this.recepents.split(",")){
            recepents.add(s);
        }

        return recepents;
    }

    public void sendMessage(){
//        send sms
//        update status
    }
}
