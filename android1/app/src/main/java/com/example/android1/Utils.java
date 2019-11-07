package com.example.android1;

import android.util.Log;

public class Utils {
    public static final boolean LOG_INFO = true;
    public static final String LOG_TAG = "Android1";
    public static void info(String msg) {
        if (LOG_INFO) {
            Log.i(LOG_TAG, msg);
        }
    }
}
