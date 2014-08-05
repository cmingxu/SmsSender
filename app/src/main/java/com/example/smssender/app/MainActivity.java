package com.example.smssender.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    public static final int JSON_RECEIVED = 1;
    public static final int SMS_SENT = 2;

    public Handler uiUpdator;
    public Button btnStop;
    public Button btnStart;
    public TextView logMessageArea;

    public boolean running;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initUIHandler();

        this.running = true;
        Intent intent = new Intent(MainActivity.this, SmsSenderService.class);
        startService(intent);

    }

    public void initUI(){
        this.btnStart = (Button)findViewById(R.id.startBtn);
        this.btnStop  = (Button)findViewById(R.id.stopBtn);
        this.logMessageArea = (TextView)findViewById(R.id.textView);
        if(this.running){
            this.btnStart.setEnabled(false);
        }

        this.btnStart.setOnClickListener(new BtnOnClickListener());
        this.btnStop.setOnClickListener(new BtnOnClickListener());
    }

    class  BtnOnClickListener implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.startBtn){
                MainActivity.this.running = true;
                MainActivity.this.btnStart.setEnabled(false);
                MainActivity.this.btnStop.setEnabled(true);
            }{
                MainActivity.this.running = false;
                MainActivity.this.btnStart.setEnabled(true);
                MainActivity.this.btnStop.setEnabled(false);
            }
        }
    }

    public void initUIHandler(){
        uiUpdator = new Handler(){
            @Override
            public void handleMessage(Message msg) {
               switch (msg.what){
                   case JSON_RECEIVED:
                       info_log();
                       break;
                   case SMS_SENT:
                       info_log();
                       break;
                   default:


               }

                super.handleMessage(msg);

            }
        };
    }

    public void info_log(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
