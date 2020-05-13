package com.lcmobile.dynamicfeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lcmobile.dynamicfeature.manager.FragmentDynamicManager
import com.lcmobile.dynamicfeature.manager.installDashboard
import com.lcmobile.dynamicfeature.manager.installHome
import com.lcmobile.dynamicfeature.manager.installNotification

class MainActivity : AppCompatActivity() {

    private val navView by lazy { findViewById<BottomNavigationView>(R.id.nav_view) }

    private val dynamicManager by lazy {
        FragmentDynamicManager(
            this,
            supportFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_notifications -> {
                    dynamicManager.installNotification(::inflate)
                }
                R.id.navigation_dashboard -> {
                    dynamicManager.installDashboard(::inflate)
                }
                R.id.navigation_home -> {
                    dynamicManager.installHome(::inflate)
                }
            }
            true
        }
        navView.selectedItemId = R.id.navigation_home
    }

    private fun inflate(fragment: Fragment?) {
        if (fragment == null) {
            //Do message error
            return
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
