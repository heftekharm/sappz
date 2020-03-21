package com.hfm.sappz.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModel

class GradeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun save(context:Context, field:String,grade:Int){
        LocalStorage.saveField(context,field)
        LocalStorage.saveGrade(context,grade)
    }

}
