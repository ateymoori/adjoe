package com.adjoe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.adjoe.utils.Constants.TIME;

public class SharedPrefHelper {

    public static void setLong(Context context, Long time) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(TIME, time);
        editor.apply();
    }

    public static Long getLong(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
        return prefs.getLong(TIME, ((long) (0)));
    }


}
