package com.vnpt.mydailyfitness.HustCare

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class AppUtils {
    companion object{
        const val USERS_SHARED_PREF="user_pref"
        const val WEIGHT_KEY="weight"
        const val WORK_TIME="workTime"
        const val WAKEUP_TIME="wakeupTime"
        const val SLEEPING_TIME="sleepingTime"
        const val TOTAL_INTAKE="totalIntake"
        const val FIRST_LAUNCH="firstLaunch"
        const val NOTIFICATION_STATUS_KEY="notificationstatus"
        const val NOTIFICATION_FREQUENCY_KEY="notificationfrequency"
        const val NOTIFICATION_MSG_KEY="notificationmsg"
        const val NOTIFICATION_TONE_URI_KEY = "notificationtone"
        fun calculateTotalIntake(weight:Int,workTime:Int): Double {
            return ((weight*100/3.0+workTime*6/7))
        }
        @SuppressLint("SimpleDateFormat")
        fun dateFormat():String{
            val date=Calendar.getInstance().time
            val sdf=SimpleDateFormat("dd/MM/yyyy")
            return sdf.format(date)
        }
    }
}