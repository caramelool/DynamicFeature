package com.lcmobile.dynamicfeature.manager

import androidx.fragment.app.Fragment

fun FragmentDynamicManager.installHome(
    onComplete: (Fragment) -> Unit
) {
    val moduleName = "home_dynamic"
    val className = "com.lcmobile.home.HomeFragment"
    install(moduleName, className, onComplete)
}

fun FragmentDynamicManager.installDashboard(
    onComplete: (Fragment) -> Unit
) {
    val moduleName = "dashboard_dynamic"
    val className = "com.lcmobile.dashboard.DashboardFragment"
    install(moduleName, className, onComplete)
}

fun FragmentDynamicManager.installNotification(
    onComplete: (Fragment) -> Unit
) {
    val moduleName = "notification_dynamic"
    val className = "com.lcmobile.notification.NotificationsFragment"
    install(moduleName, className, onComplete)
}