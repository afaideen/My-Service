package uithreadsdemo.youtube.com.uithreaddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by han on 23/2/2018.
 */

public class MyHelper{
    static final String TAG = MyHelper.class.getSimpleName();
    private static Handler mHandler;
    private static int h;


    public static void SavedSharedPref(Context context, String username) {
        Log.d(TAG, "Saving username: " + username);
        SharedPreferences savedValues = context.getSharedPreferences("SavedValues", MODE_PRIVATE);
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("UserName", username);
        editor.commit();
    }

    public static String LoadSharedPref(Context context) {
        SharedPreferences savedValues = context.getSharedPreferences("SavedValues", MODE_PRIVATE);
        String value = savedValues.getString("UserName", null);  //load saved value!
        return value;
    }

    public static void SavedSharedPref(Context context, boolean serviceBound) {
        Log.d(TAG, "Saving serviceBound: " + serviceBound);
        SharedPreferences savedValues = context.getSharedPreferences("SavedValues", MODE_PRIVATE);
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putBoolean("serviceBound", serviceBound);
        editor.commit();
    }
    public static boolean LoadSharedPrefServiceBound(Context context) {
        SharedPreferences savedValues = context.getSharedPreferences("SavedValues", MODE_PRIVATE);
        boolean value = savedValues.getBoolean("serviceBound", false);  //load saved value!
        return value;
    }


    public static BroadcastReceiver registerReceiver(Context context, String action, BroadcastReceiver receiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
//        if(receiver == null) {
//            receiver = new ChatListFragment.MyBroadcastReceiver();
        context.registerReceiver(receiver, intentFilter);
        Log.d(TAG, "register receiver: " + action);
//        }
        return receiver;
    }

    public static BroadcastReceiver unregisterReceiver(Context context, String action, BroadcastReceiver receiver) {
        if(receiver != null) {
            context.unregisterReceiver(receiver);
            receiver = null;
            Log.d(TAG, "unregister receiver: " + action);
        }
        return receiver;
    }
    public static void sendBroadcast(Context mContext, String action, Object msg) {
        Intent mainintent = new Intent();
        mainintent.setAction(action);
        mainintent.putExtra("paramMsg", (Serializable) msg);
        mContext.sendBroadcast(mainintent);
    }
}
