package com.lcmobile.dynamicfeature

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lcmobile.dashboard.DashboardFragment
import com.lcmobile.home.HomeFragment
import com.lcmobile.notification.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private val navView by lazy { findViewById<BottomNavigationView>(R.id.nav_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.navigation_notifications -> NotificationsFragment()
                R.id.navigation_dashboard -> DashboardFragment()
                else -> HomeFragment()
            }
            inflate(fragment)
            true
        }

        navView.selectedItemId = R.id.navigation_home
    }

    private fun inflate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
