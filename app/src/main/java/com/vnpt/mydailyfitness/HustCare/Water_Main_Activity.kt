package com.vnpt.mydailyfitness.HustCare

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vnpt.mydailyfitness.R

class Water_Main_Activity : AppCompatActivity() {
    private lateinit var waterReminderDb:WaterReminderDb
    private var inTook:Int=0
    private var totalIntake:Int=0
    private lateinit var dateNow:String
    private lateinit var sharePref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.water_activity_main)
        dateNow=AppUtils.dateFormat()
        sharePref=getSharedPreferences(AppUtils.USERS_SHARED_PREF, MODE_PRIVATE)
        totalIntake=sharePref.getInt(AppUtils.TOTAL_INTAKE,0)
        if(totalIntake <= 0){
            startActivity(Intent(this,InitUserInfo::class.java))
            finish()
        }

    }
}