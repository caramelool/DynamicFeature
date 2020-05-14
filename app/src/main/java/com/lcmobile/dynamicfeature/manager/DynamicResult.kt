package com.lcmobile.dynamicfeature.manager

sealed class DynamicResult {
    object Downloading : DynamicResult()
    object Installed : DynamicResult()
    object Loading : DynamicResult()
    class Other(val status: Int) : DynamicResult()
}