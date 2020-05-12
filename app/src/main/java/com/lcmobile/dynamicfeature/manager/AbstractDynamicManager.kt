package com.lcmobile.dynamicfeature.manager

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

abstract class AbstractDynamicManager(context: Context) {

    private val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(context)
    }

    protected open fun install(moduleName: String, onStatusChange: (DynamicResult) -> Unit) {
        if (manager.installedModules.contains(moduleName)) {
            val result = DynamicResult.Installed
            onStatusChange.invoke(result)
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        manager.startInstall(request)
            .addOnCompleteListener {
                val result = DynamicResult.Installed
                onStatusChange.invoke(result)
            }
            .addOnSuccessListener {
                val result = DynamicResult.Download
                onStatusChange.invoke(result)
            }
            .addOnFailureListener {
                val result = DynamicResult.Other(0)
                onStatusChange.invoke(result)
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
//                        DynamicResult.Other(status ?: 0)
//                    }
//                }
//                onStatusChange.invoke(result)
//                manager.unregisterListener(this)
//            }
//        })

    }
}