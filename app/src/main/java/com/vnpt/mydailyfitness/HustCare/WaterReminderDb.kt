package com.vnpt.mydailyfitness.HustCare

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class WaterReminderDb(val context:Context) : SQLiteOpenHelper(
        context, DATABASE_NAME,null, VERSION
) {
    companion object{
        private const val DATABASE_NAME="water_manager"
        private const val TABLE_STATS ="water_Db"
        private const val VERSION=1
        private const val KEY_ID="id"
        private const val KEY_DATE="date"
        private const val KEY_INTOOK="intook"
        private const val TOTAL_INTAKE="totalIntake"
        private const val  TAG="WaterReminderDb"
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_REMINDER_DATABASE="CREATE TABLE " + TABLE_STATS + " (" +
                KEY_ID + "  INTEGER PRIMARY KEY, " +
                KEY_DATE + " TEXT, " +
                KEY_INTOOK + " INT, "+
                TOTAL_INTAKE + " INT) "
        p0?.execSQL(CREATE_REMINDER_DATABASE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS)
        onCreate(p0)
    }
    fun addAll(date:String,intook:Int,totalIntake:Int) : Long{
        if(checkDbExistence(date)==0) {
            val contentValues = ContentValues()
            contentValues.put(KEY_DATE, date)
            contentValues.put(KEY_INTOOK, intook)
            contentValues.put(TOTAL_INTAKE, totalIntake)
            val sqLiteDb = this.writableDatabase
            val add = sqLiteDb.insert(TABLE_STATS, null, contentValues)
            Log.d(TAG, "Add $TAG successfully")
            sqLiteDb.close()
            return add
        }
        return -1
    }
    @SuppressLint("Range")
    fun getIntook(date:String): Int {
        val selectQuery="SELECT $KEY_INTOOK FROM $TABLE_STATS WHERE $KEY_DATE = $date"
        val sqLiteDb=this.readableDatabase
        val cursor=sqLiteDb.rawQuery(selectQuery,arrayOf(date))
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndex(KEY_INTOOK))
        }
        return 0
    }

    fun addIntook(date:String, selectedOption:Int) : Int{
        val inTook=getIntook(date)
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_INTOOK,inTook + selectedOption)
        val update=db.update(TABLE_STATS,contentValues,"$KEY_DATE=$date", arrayOf(date))
        db.close()
        return update
    }
    fun getAllStats() : Cursor{
        val selectQuery="SELECT * FROM $TABLE_STATS"
        val db=this.readableDatabase
        return db.rawQuery(selectQuery,null)
    }
    fun updateTotalIntake(date:String,totalIntake:Int):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(TOTAL_INTAKE,totalIntake)
        val update=db.update(TABLE_STATS,contentValues,"$KEY_DATE=$date", arrayOf(date))
        db.close()
        return update
    }

    fun checkDbExistence(date:String) : Int{
        val selectQuery = "SELECT $KEY_INTOOK FROM $TABLE_STATS WHERE $KEY_DATE = $date"
        val sqLiteDb=this.readableDatabase
        val cursor = sqLiteDb.rawQuery(selectQuery, arrayOf(date))
        if(cursor.moveToFirst()){
            return cursor.count
        }
        return 0
    }

}