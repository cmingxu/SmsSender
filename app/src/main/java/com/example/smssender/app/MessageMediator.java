package com.example.smssender.app;

import android.text.format.Time;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by xcm on 8/5/14.
 */
public class MessageMediator {
    public static final String LOG_TAG = "SmsSenderService";
   public static final String SERVER_BASE = "http://192.168.0.106:3000/messages.json";
    public  HttpClient client;
    public StringBuilder builder;
    public JSONArray jsonArray;
    HttpGet get;
    HttpPost post;


    public MessageMediator(){
        this.client = new DefaultHttpClient();
        this.builder = new StringBuilder();
        this.jsonArray = null;

        this.post = new HttpPost(SERVER_BASE);
    }

    public ArrayList<Message> getMessages(){
        ArrayList<Message> arrayList = new ArrayList<Message>();
        this.get = new HttpGet(SERVER_BASE + "?last_sync=" + System.currentTimeMillis()/1000);
        try {
            HttpResponse response = client.execute(get);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                builder.append(s);
            }

            jsonArray = new JSONArray(builder.toString());
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Message message = new Message();
                message.recepents = jsonObject.getString("mobile_num");
                message.message_content = jsonObject.getString("content");
                arrayList.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  arrayList;
    }

    public void markSent(){

    }

}
