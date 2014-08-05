package com.example.smssender.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

    public void sendMessage(Context context){
        Intent intentt = new Intent(Intent.ACTION_VIEW);
        intentt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intentt.setData(Uri.parse("sms:"));
        intentt.setType("vnd.android-dir/mms-sms");
        intentt.putExtra(Intent.EXTRA_TEXT, this.message_content);

        intentt.putExtra("address",  this.recepents);
        context.startActivity(intentt);
//        send sms
//        update status
    }
}
