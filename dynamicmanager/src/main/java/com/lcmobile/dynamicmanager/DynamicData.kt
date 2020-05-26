package com.lcmobile.dynamicmanager

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DynamicData(val key: String, val value: Int) : Parcelable {
    companion object {
        const val KEY = "key_dynamic_data"
    }
}

interface DynamicDataProvider {
    fun provideData(): DynamicData?
}