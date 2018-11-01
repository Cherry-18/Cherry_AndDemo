package com.cherry.cherry_anddemo.ui.interview.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverOne extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receivingan Intent broadcast.

        if (intent.getAction().equals("com.cherry.broadcast.test")){
            Toast.makeText(context, "BroadcastReceiverOne"+intent.getAction(), Toast.LENGTH_LONG).show();
            Log.e("HHH=======","BroadcastReceiverOne====="+intent.getAction());
        }else if (intent.getAction().equals("com.cherry.broadcast.test1")){
            Bundle bundle = getResultExtras(true);
            String msg = bundle.getString("msg");
            Toast.makeText(context, "BroadcastReceiverOne"+intent.getAction()+msg, Toast.LENGTH_LONG).show();
            Log.e("HHH=======","BroadcastReceiverOne====="+intent.getAction()+"======"+msg);

        }else if (intent.getAction().equals("com.cherry.broadcast.test2")){
            Toast.makeText(context, "BroadcastReceiverOne"+intent.getAction(), Toast.LENGTH_LONG).show();
            Log.e("HHH=======","BroadcastReceiverOne====="+intent.getAction());

        }

    }
}
