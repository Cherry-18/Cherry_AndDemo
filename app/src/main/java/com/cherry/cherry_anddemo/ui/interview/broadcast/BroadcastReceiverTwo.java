package com.cherry.cherry_anddemo.ui.interview.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverTwo extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.cherry.broadcast.test")){
            Toast.makeText(context, "BroadcastReceiverTwo"+intent.getAction(), Toast.LENGTH_LONG).show();
            Log.e("HHH=======","BroadcastReceiverTwo======"+intent.getAction());
//            abortBroadcast();
        }else if (intent.getAction().equals("com.cherry.broadcast.test1")){
//            abortBroadcast();
//            Bundle bundle =  getResultExtras(true); //从activity过来的数据一直是空,是否从activity过来的不能用这个
//            String msg = bundle.getString("msg");

            String msg = intent.getStringExtra("msg");

            Bundle bundle = new Bundle();
            bundle.putString("msg","我是修改后信息");
            setResultExtras(bundle);
            Toast.makeText(context, "BroadcastReceiverTwo"+intent.getAction()+msg, Toast.LENGTH_LONG).show();
            Log.e("HHH=======","BroadcastReceiverTwo======"+intent.getAction()+"======"+msg);

        }else if (intent.getAction().equals("com.cherry.broadcast.test2")){
            Toast.makeText(context, "BroadcastReceiverTwo"+intent.getAction(), Toast.LENGTH_LONG).show();
            Log.e("HHH=======","BroadcastReceiverTwo======"+intent.getAction());

        }
    }
}
