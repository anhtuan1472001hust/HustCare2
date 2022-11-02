package com.vnpt.mydailyfitness.HustCare

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        val pref = p0?.getSharedPreferences(AppUtils.USERS_SHARED_PREF,MODE_PRIVATE)
    }
}