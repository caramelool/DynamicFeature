package com.lcmobile.dynamicfeature.manager

sealed class DynamicResult {
    object Download : DynamicResult()
    object Installed : DynamicResult()
    object Error : DynamicResult()
}