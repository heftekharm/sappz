package com.hfm.sappz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfm.sappz.ui.main.MainFragment
import com.parse.Parse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
       /* if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }*/


    }

}
