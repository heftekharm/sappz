package com.hfm.sappz.ui.main

import android.content.Context
private const val FIELD_KEY:String="field"
private const val GRADE_KEY:String="grade"
private const val LOGGED_IN_AS_STUDENT_KEY="is_student"
object LocalStorage {

    var field:String?=null
    fun saveField(context:Context,field:String){
        save(context, FIELD_KEY,field)
    }

    fun getField(context:Context):String?{
        val sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(FIELD_KEY,null)
    }

    fun saveGrade(context:Context,grade:Int){
        save(context, GRADE_KEY,grade)
    }

    fun getGrade(context: Context):Int {
        val sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(GRADE_KEY,-1)
    }

    fun saveLoginType(context:Context,isStudent:Boolean){
        save(context,LOGGED_IN_AS_STUDENT_KEY,isStudent)
    }
    fun loggedInAsStudent(context:Context):Boolean{
        val sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(LOGGED_IN_AS_STUDENT_KEY,false)

    }

    fun save(context: Context, key:String, value:Any){
        val sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        val edit=sharedPreferences.edit()

        when (value){
            is Int->edit.putInt(key,value)
            is String->edit.putString(key,value)
            is Boolean->edit.putBoolean(key,value)
        }
        edit.commit()
    }


}