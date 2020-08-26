package com.lcmobile.dynamicfeature

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lcmobile.dynamicmanager.FragmentDynamicManager

class MainActivity : BaseSplitActivity() {

    private val navView by lazy { findViewById<BottomNavigationView>(R.id.nav_view) }
    private val loading by lazy { findViewById<ProgressBar>(R.id.loading) }
    private val host by lazy { findViewById<View>(R.id.nav_host_fragment) }

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
            showLoading()
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



    private fun showLoading() {
        loading.visibility = View.VISIBLE
        host.visibility = View.GONE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
        host.visibility = View.VISIBLE
    }

    private fun inflate(fragment: Fragment?) {
        hideLoading()
        if (fragment == null) {
            //Do message error
            return
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
