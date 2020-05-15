package com.lcmobile.dynamicmanager

sealed class DynamicResult {
    object Downloading : DynamicResult()
    object Installed : DynamicResult()
    class Other(val status: Int) : DynamicResult()
}