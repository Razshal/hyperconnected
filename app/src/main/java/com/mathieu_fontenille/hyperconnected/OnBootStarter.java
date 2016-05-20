package com.mathieu_fontenille.hyperconnected;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Mathieu on 19/05/2016.
 */
public class OnBootStarter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, ListenerService.class);
        context.startService(startServiceIntent);
        Log.d("hypercov2", "Service onBoot OK, launching service");
    }
}
