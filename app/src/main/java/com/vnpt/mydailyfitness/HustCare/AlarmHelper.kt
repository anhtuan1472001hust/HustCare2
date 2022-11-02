package com.vnpt.mydailyfitness.HustCare

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import com.vnpt.mydailyfitness.R

class AlarmHelper(val context: Context) {
    private val CHANNEL_ID="com.vnpt.mydailyfitness.CHANNELONE"
    private fun createChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelName=context.resources.getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID,channelName,importance)
            mChannel.enableLights(true)
            mChannel.lightColor=Color.BLUE
            mChannel.setShowBadge(true)
            mChannel.enableVibration(true)
            mChannel.lockscreenVisibility=Notification.VISIBILITY_PUBLIC

        }
    }

}