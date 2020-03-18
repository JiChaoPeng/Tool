package com.android.frameworktool.util

import android.Manifest.permission.READ_PHONE_STATE
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import kotlin.math.max
import kotlin.math.min


fun Context?.screenWidth(): Int {
    return Size.screenWidth(this)
}

fun Context?.getStatusBarHeight(): Int {
    return if (this != null) {
        val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            Resources.getSystem().getDimensionPixelSize(resourceId)
        } else {
            0
        }
    } else {
        0
    }
}

fun Context?.screenHeight(): Int {
    return Size.screenHeight(this)
}

fun Context?.getNavigationBarHeight(): Int {
    val navigationBarHeight = if (this != null) {
        Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android"))
    } else {
        0
    }

    val display: WindowManager? = this?.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    val realPoint = Point()
    val point = Point()
    display?.defaultDisplay?.getRealSize(realPoint)
    display?.defaultDisplay?.getSize(point)
    return if (realPoint.y != point.y && realPoint.y - point.y >= navigationBarHeight) {
        navigationBarHeight
    } else {
        0
    }
}

fun Context.showInputMethod(view: View) {
    if (view.requestFocus()) {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Context.hideInputMethod(view: View? = null) {
    when {
        view != null -> view.windowToken
        this is Activity -> this.findViewById<View>(android.R.id.content).windowToken
        else -> null
    }?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(it, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Context?.dip(value: Int): Int {
    return if (this != null) {
        (value * resources.displayMetrics.density).toInt()
    } else {
        0
    }
}

fun Context?.dip(value: Float): Int {
    return if (this != null) {
        (value * resources.displayMetrics.density).toInt()
    } else {
        0
    }
}

fun Context?.dipToFloat(value: Float): Float {
    return if (this != null) {
        value * resources.displayMetrics.density
    } else {
        0f
    }
}

fun Context.sp2px(spValue: Int): Int {
    val fontScale = resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

fun Context.getDrawableCompat(id: Int): Drawable {
    return resources.getDrawable(id, null)

}

fun Context.copy(text: String?) {
    text ?: return
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(ClipData.newPlainText(MIMETYPE_TEXT_PLAIN, text))
}

fun Context.getStoreName(): String {
    return this.packageManager.getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
            .metaData.getString("ANDROID_STORE", "")
}

fun Context.gotoSystemSetting() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", this.packageName, null)
    intent.data = uri
    this.startActivity(intent)
}

object Size {
    var windowHeight: Int = 0

    var screenWidth: Int = 0
        private set
    var screenHeight: Int = 0
        private set

    fun cacheSize(context: Context?) {
        context?.screenWidth()
        context?.screenHeight()
    }

    private fun screenSize(context: Context?): Point? {
        val display: WindowManager? = context?.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        val point = Point()
        display?.defaultDisplay?.getSize(point)
        return point
    }

    fun screenWidth(context: Context?): Int {
        if (screenWidth == 0) {
            screenWidth = screenSize(context)?.x ?: 1
        }
        return min(screenWidth, screenHeight)
    }

    fun screenHeight(context: Context?): Int {
        if (screenHeight == 0) {
            screenHeight = screenSize(context)?.y ?: 1
        }
        return max(screenHeight, screenWidth)
    }

    private fun h2w(context: Context?): Float {
        return screenHeight(context).toFloat() / screenWidth(context).toFloat()
    }

    fun isFullScreenPhone(context: Context?): Boolean {
        return h2w(context) >= 18f / 9f
    }

    fun dip(value: Int, context: Context?): Int = context?.dip(value) ?: 0
    fun dip(value: Float, context: Context?): Int = context?.dip(value) ?: 0
}
