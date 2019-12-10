package com.fhad.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvMessage;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            if (message != null){
                tvMessage.append("to produce" + message +"\n");
                Intent sendIntent = new Intent("com.fhad.producer.action.consumer");
                sendIntent.setPackage("com.fhad.producer");
                sendBroadcast(sendIntent);
            }else{
                tvMessage.append("Error something wrong\n");
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessage = findViewById(R.id.tv_in_messages);
        IntentFilter filter = new IntentFilter("com.fhad.consumer.action.producer");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
