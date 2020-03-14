package com.hfm.sappz

import android.app.Application
import com.parse.Parse
import okhttp3.OkHttpClient

class SappzApp:Application() {
    override fun onCreate() {
        super.onCreate()
        val appId="sappzisaschoolapp"
        val server="http://185.53.141.150:3500/parse"
        Parse.initialize(Parse.Configuration.Builder(this).applicationId(appId).server(server).enableLocalDataStore().build())

    }

}