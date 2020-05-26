package com.lcmobile.dynamicmanager

sealed class DynamicStatus {
    object Downloading : DynamicStatus()
    object Installed : DynamicStatus()
    object Failed : DynamicStatus()
    data class Other(val status: Int) : DynamicStatus()
}