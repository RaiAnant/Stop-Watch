package com.example.anantrai.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int mseconds=0;
    boolean on=false,wasRunning;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            mseconds=savedInstanceState.getInt("seconds");

            on=savedInstanceState.getBoolean("on");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        textView=(TextView)findViewById(R.id.time);

        timerThread();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",mseconds);

        savedInstanceState.putBoolean("on",on);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }


    public void start(View view){
        on=wasRunning=true;
    }

    public void stop(View view){
        on=wasRunning=false;
    }

    public void reset(View view){
        on=wasRunning=false;
        mseconds=0;
    }


    public void timerThread(){
        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                String time=String.format("%02d:%02d:%02d:%02d",mseconds/216000,(mseconds/3600)%60,(mseconds/60)%60,mseconds%60);

                textView.setText(time);

                if(on){
                    mseconds++;
                }
                handler.postDelayed(this,17);
            }
        };
        handler.post(runnable);
    }
}
