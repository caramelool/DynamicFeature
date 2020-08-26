package com.lcmobile.detail_dynamic

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.lcmobile.dynamicfeature.BaseSplitActivity
import com.lcmobile.dynamicmanager.DynamicData
import com.lcmobile.dynamicmanager.DynamicDataProvider

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseSplitActivity(),
    DynamicDataProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun provideData(): DynamicData? {
        return intent?.getBundleExtra("dynamic")?.getParcelable(DynamicData.KEY)
    }
}
