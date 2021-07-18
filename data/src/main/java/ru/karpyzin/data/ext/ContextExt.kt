package ru.karpyzin.data.ext

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import timber.log.Timber

//region UserAgent

fun Context.getUserAgent(): String {
    return "Heyka " +
            getVersionName() +
            " (Android ${Build.VERSION.RELEASE}" +
            "; Scale/${this.resources.displayMetrics.density})"
}

private fun Context.getVersionName(): String {
    try {
        val packageInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        return packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        Timber.e("Error get version name")
    }
    return "1.0"
}

//endregion