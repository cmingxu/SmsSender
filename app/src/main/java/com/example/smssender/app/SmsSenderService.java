package com.example.smssender.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;

public class SmsSenderService extends Service {
    public static final String LOG_TAG = "SmsSenderService";
    public static final int SLEEP_INTERVAL = 1000  * 10;
    public MessageMediator messageMediator;

    public SmsSenderService() {
        messageMediator = new MessageMediator();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        Thread t = new Thread(new Worker());

        t.start();
        return super.onStartCommand(intent, flags, startId);
    }


    class Worker implements Runnable{

        @Override
        public void run() {
            try {
                while(true) {
                    Log.d(LOG_TAG, "Worker working");
                    for(Message m : messageMediator.getMessages()){
                        m.sendMessage(SmsSenderService.this);
                        Log.d(LOG_TAG, m.getMessage_content());
                    }
                    Thread.sleep(SLEEP_INTERVAL);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
