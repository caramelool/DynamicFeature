package com.lcmobile.dynamicmanager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class FragmentDynamicManager(
    private val context: Context,
    private val fragmentManager: FragmentManager
) : AbstractDynamicManager(context) {

    fun install(moduleName: String, fragmentClassName: String, onComplete: (Fragment?) -> Unit) {
        super.install(moduleName) {
            when (it) {
                is DynamicStatus.Installed -> {
                    val fragment = fragmentManager.fragmentFactory
                        .instantiate(context.classLoader, fragmentClassName)
                    onComplete.invoke(fragment)
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