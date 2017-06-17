package com.sms_app;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    SmsManager sm;
    Button b;
    PendingIntent SentIntent, deliveredIntent;
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                try {

//                Toast.makeText(MainActivity.this,num, Toast.LENGTH_SHORT).show();


                    msg.setTitle("Alert Box");
                    msg.setMessage("Do you want to send the message?");
                    msg.setCancelable(true);
                    msg.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String str;
                            str = e2.getText().toString();
                            String num;
                            num = e1.getText().toString();
                            SentIntent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent("sending"), 0);
                            deliveredIntent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent("deliver"), 0);
                            sm = SmsManager.getDefault();
                            sm.sendTextMessage(num, null, str, SentIntent, deliveredIntent);
                            Toast.makeText(MainActivity.this, "SENT SUCCESSFULLY", Toast.LENGTH_LONG).show();

                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                msg.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                msg.create();
                msg.show();
            }
        });

    }
}

