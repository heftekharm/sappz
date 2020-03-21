package com.hfm.sappz

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import com.parse.Parse
import java.util.*

class SappzApp:Application() {
/*
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocalHelper.setLocale(base,"ar"))
    }
*/


    override fun onCreate() {
        super.onCreate()
        val appId="sappzisaschoolapp"
        val server="http://185.53.141.150:3500/parse"
        Parse.initialize(Parse.Configuration.Builder(this).applicationId(appId).server(server).build())

    }

    override fun createConfigurationContext(overrideConfiguration: Configuration): Context {
        val locale = Locale("fa")
        overrideConfiguration.setLocale(locale)
        overrideConfiguration.setLayoutDirection(locale)
        return super.createConfigurationContext(overrideConfiguration)
    }
}