package com.lcmobile.dynamicfeature

import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Testing Dynamic Features
 *
 * Codelab: https://codelabs.developers.google.com/codelabs/on-demand-dynamic-delivery
 * Medium: https://proandroiddev.com/developing-your-own-dynamic-feature-3c48378e3065
 */

class MainApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}