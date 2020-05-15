package com.lcmobile.dynamicmanager

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import timber.log.Timber

abstract class AbstractDynamicManager(context: Context) {

    private val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(context)
    }

    protected open fun install(moduleName: String, onStatusChange: (DynamicResult) -> Unit) {
        if (manager.installedModules.contains(moduleName)) {
            installed(moduleName, onStatusChange)
            return
        }

        Timber.d("Downloading $moduleName")
        onStatusChange.invoke(DynamicResult.Downloading)

        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        manager.startInstall(request)
            .addOnCompleteListener {
                installed(moduleName, onStatusChange)
            }
            .addOnSuccessListener {
                Timber.d("Loading $moduleName")
                val result = DynamicResult.Loading
                onStatusChange.invoke(result)
            }
            .addOnFailureListener {
                Timber.d("Error Loading $moduleName")
                val result = DynamicResult.Other(SplitInstallSessionStatus.FAILED)
                onStatusChange.invoke(result)
            }
    }
// More Status
//        manager.registerListener(object : SplitInstallStateUpdatedListener {
//            override fun onStateUpdate(state: SplitInstallSessionState?) {
//                val result = when (val status = state?.status()) {
//                    SplitInstallSessionStatus.DOWNLOADING -> {
//                        Timber.d("$moduleName DOWNLOADING")
//                        DynamicResult.Download
//                    }
//                    SplitInstallSessionStatus.INSTALLED -> {
//                        Timber.d("$moduleName INSTALLED")
//                        DynamicResult.Installed
//                    }
//                    else -> {
//                        Timber.d("$moduleName OTHER $status")
//                        DynamicResult.Other(status ?: SplitInstallSessionStatus.FAILED)
//                    }
//                }
//                onStatusChange.invoke(result)
//                manager.unregisterListener(this)
//            }
//        })

    private fun installed(moduleName: String, onStatusChange: (DynamicResult) -> Unit) {
        Timber.d("Module $moduleName installed")
        val result = DynamicResult.Installed
        onStatusChange.invoke(result)
    }
}