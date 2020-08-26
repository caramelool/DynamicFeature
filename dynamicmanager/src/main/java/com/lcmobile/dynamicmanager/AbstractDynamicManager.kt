package com.lcmobile.dynamicmanager

import android.content.Context
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import timber.log.Timber

typealias DynamicStatusChangeListener = (DynamicStatus) -> Unit

abstract class AbstractDynamicManager(context: Context) {

    private var onStatusChange: DynamicStatusChangeListener? = null

    private val listener = SplitInstallStateUpdatedListener { state ->
        val result = when (val status = state?.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                DynamicStatus.Downloading
            }
            SplitInstallSessionStatus.INSTALLED -> {
                DynamicStatus.Installed
            }
            SplitInstallSessionStatus.FAILED -> {
                DynamicStatus.Failed
            }
            else -> {
                DynamicStatus.Other(status ?: SplitInstallSessionStatus.FAILED)
            }
        }
        Timber.d("AbstractDynamicManager status $result")
        onStatusChange?.invoke(result)
    }

    private val manager: SplitInstallManager by lazy {
        if (BuildConfig.DEBUG) {
            FakeSplitInstallManagerFactory.create(
                context,
                context.getExternalFilesDir("local_testing")
            ).apply {
//                setShouldNetworkError(true)
            }
        } else {
            SplitInstallManagerFactory.create(context)
        }.apply {
            registerListener(listener)
        }
    }

    protected open fun install(moduleName: String, onStatusChange: DynamicStatusChangeListener) {
        this.onStatusChange = onStatusChange
        if (manager.installedModules.contains(moduleName)) {
            Timber.d("Module $moduleName installed")
            val result = DynamicStatus.Installed
            onStatusChange.invoke(result)
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        manager.startInstall(request)
    }
}