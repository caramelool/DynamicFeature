package com.lcmobile.dashboard

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcmobile.dynamicmanager.ActivityDynamicManager

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val activityDynamicManager by lazy {
        ActivityDynamicManager(application)
    }

    private val _dataView = MutableLiveData<DataView>().apply {
        value = DataView(
            "This is dashboard Fragment",
            "Open Detail"
        )
    }
    val dataView: LiveData<DataView> = _dataView

    fun openDetail(listener: (Intent?) -> Unit) {
        activityDynamicManager.install("jajaja", "dynamic://detail", listener)
    }
}

data class DataView(
    val text: String,
    val button: String
)