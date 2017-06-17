package com.sms_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by HP on 12-06-2017.
 */

public class hello extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        if(bundle!=null)
        {
            Object [] ab=(Object[])bundle.get("pdus");
            for(int i=0;i<ab.length;i++)
            {
                SmsMessage sms=SmsMessage.createFromPdu((byte[])ab[i]);
                String ph=sms.getDisplayOriginatingAddress();
                String msg=sms.getDisplayMessageBody();
                Toast.makeText(context,"phone "+ph.toString()+"sends "+msg.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
