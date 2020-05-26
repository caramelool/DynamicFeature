package com.lcmobile.dynamicmanager

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class ActivityDynamicManager(context: Context) : AbstractDynamicManager(context) {

    fun install(moduleName: String, deeplink: String, onComplete: (Intent?) -> Unit) {
        super.install(moduleName) {
            when (it) {
                is DynamicStatus.Installed -> {
                    val intent = Intent()
                        .setData(Uri.parse(deeplink))
                    onComplete.invoke(intent)
                }
                is DynamicStatus.Other -> {
                    if (it.status == SplitInstallSessionStatus.FAILED) {
                        onComplete.invoke(null)
                    }
                }
            }
        }
    }

}