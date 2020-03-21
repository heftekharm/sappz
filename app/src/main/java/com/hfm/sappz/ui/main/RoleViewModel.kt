package com.hfm.sappz.ui.main

import android.os.storage.StorageManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfm.sappz.R
import com.parse.ParseUser


class RoleViewModel : ViewModel() {
    private val tag="RoleViewModel"
    val loginRespLive: MutableLiveData<LoginResponse> = MutableLiveData();

    fun logInAsTeacher(username:String?, password:String?){
        login(username,password)
    }

    fun logInAsStudent(){
        val uname="student1"
        val pass="1student"
        login(uname,pass)
    }

    fun login(uname: String?,pass: String?){
        ParseUser.logOut()
        ParseUser.logInInBackground(uname,pass) { user, e ->
            val isAuth=user?.isAuthenticated ?: false
            val msgStringId=when(e?.code ?: 0){
                100->R.string.role_network_error_msg
                101->R.string.role_login_error_msg
                else ->R.string.role_unknown_error_msg
            }
            e?.printStackTrace()
            loginRespLive.value=LoginResponse(msgStringId,isAuth);
        }
    }
}




data class LoginResponse(val msgStringId:Int?,val isAuth:Boolean)