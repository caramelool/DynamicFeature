package com.lcmobile.dynamicfeature.manager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentDynamicManager(
    private val context: Context,
    private val fragmentManager: FragmentManager
) : AbstractDynamicManager(context) {
    fun install(moduleName: String, fragmentClassName: String, onComplete: (Fragment) -> Unit) {
        super.install(moduleName) {
            when(it) {
                is DynamicResult.Installed -> {
                    val fragment = fragmentManager.fragmentFactory
                        .instantiate(context.classLoader, fragmentClassName)
                    onComplete.invoke(fragment)
                }
            }
        }
    }
}