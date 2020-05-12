package com.lcmobile.dynamicfeature.manager

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import timber.log.Timber

abstract class AbstractDynamicManager(
    context: Context
) {

    private val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(context)
    }

    protected open fun install(moduleName: String, onStatusChange: (DynamicResult) -> Unit) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        manager.startInstall(request)

        manager.registerListener {
            val result = when (val status = it.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    Timber.d("DOWNLOADING")
                    DynamicResult.Download
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Timber.d("INSTALLED")
                    DynamicResult.Installed
                }
                else -> {
                    Timber.d("OTHER $status")
                    DynamicResult.Error
                }
            }
            onStatusChange.invoke(result)
        }
    }
}