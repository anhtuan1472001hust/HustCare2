package com.vnpt.mydailyfitness.HustCare

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler

import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.vnpt.mydailyfitness.R
import kotlinx.android.synthetic.main.activity_init_user_info.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class InitUserInfo:AppCompatActivity() {
    private var weight:String=""
    private var workTime:String=""
    private var wakeupTime:Long=0
    private var sleepingTime:Long=0
    private lateinit var  sharePref:SharedPreferences
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_user_info)
        weight=etWeight.editText!!.text.toString()
        workTime=etWorkTime.editText!!.text.toString()
        etWakeUpTime.editText!!.setOnClickListener {
            val calendar=Calendar.getInstance()
            calendar.timeInMillis=wakeupTime
            val timerPicker = TimePickerDialog(this,
                    { timePicker, i, i2 ->
                        val date=Calendar.getInstance()
                        date.set(Calendar.HOUR_OF_DAY,i)
                        date.set(Calendar.MINUTE,i2)
                        date.timeInMillis=wakeupTime
                        etWakeUpTime.editText!!.setText(String.format("%02d:%02d",i,i2))
                    },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)
            timerPicker.setTitle("Pick your Wake Up time")
            timerPicker.show()
        }
        etSleepTime.editText!!.setOnClickListener {
            val calendar=Calendar.getInstance()
            calendar.timeInMillis=sleepingTime
            val timerPicker = TimePickerDialog(this,
                    { timePicker, i, i2 ->
                        val date=Calendar.getInstance()
                        date.set(Calendar.HOUR_OF_DAY,i)
                        date.set(Calendar.MINUTE,i2)
                        date.timeInMillis=sleepingTime
                        etSleepTime.editText!!.setText(String.format("%02d:%02d",i,i2))
                    },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)
            timerPicker.setTitle("Select your sleeping time")
            timerPicker.show()
        }
        sharePref=getSharedPreferences(AppUtils.USERS_SHARED_PREF,MODE_PRIVATE)


        btnContinue.setOnClickListener {
            val imm:InputMethodManager=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(init_user_info_parent_layout.windowToken,0)
            when{
                TextUtils.isEmpty(weight)->Snackbar.make(it,"Please input your weight",Snackbar.LENGTH_SHORT).show()
                weight.toInt() > 200 || weight.toInt() < 20 -> Snackbar.make(it,"Please input a valid weight",Snackbar.LENGTH_SHORT).show()
                TextUtils.isEmpty(workTime)->Snackbar.make(it,"Please input your work time",Snackbar.LENGTH_SHORT).show()
                workTime.toInt()<0||workTime.toInt()>1440->Snackbar.make(it,"Please input a valid work time",Snackbar.LENGTH_SHORT).show()
                else -> {
                    val editor=sharePref.edit()
                    editor.putInt(AppUtils.WEIGHT_KEY,weight.toInt())
                    editor.putInt(AppUtils.WORK_TIME,workTime.toInt())
                    editor.putLong(AppUtils.WAKEUP_TIME,wakeupTime)
                    editor.putLong(AppUtils.SLEEPING_TIME,sleepingTime)
                    editor.putBoolean(AppUtils.FIRST_LAUNCH,false)
                    val totalIntake=AppUtils.calculateTotalIntake(weight.toInt(),workTime.toInt())
                    val df=DecimalFormat("#")
                    df.roundingMode=RoundingMode.CEILING
                    editor.putInt(AppUtils.TOTAL_INTAKE,df.format(totalIntake).toInt())
                    editor.apply()
                    startActivity(Intent(this,Water_Main_Activity::class.java))
                    finish()
                }
            }

        }



    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        if(doubleBackToExitPressedOnce==true){
            Toast.makeText(this,"Press BACK again to Exit",Toast.LENGTH_SHORT).show()
            Handler().postDelayed({doubleBackToExitPressedOnce=false},1000)
        }
        finish()
    }


}