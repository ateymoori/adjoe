package com.adjoe.user_present;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.adjoe.utils.SharedPrefHelper;

public class UserPresentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPrefHelper.setLong(context, System.currentTimeMillis());
    }
}
