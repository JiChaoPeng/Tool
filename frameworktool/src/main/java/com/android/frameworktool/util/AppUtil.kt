package com.android.frameworktool.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

/**
 * Created by jichaopeng
 * 2020-02-27
 */

/**
 * 设置全屏：状态栏下层的区域也归我们控制 （View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN）
 * @param dark: true: 设置黑色背景白色字体（View.SYSTEM_UI_FLAG_LAYOUT_STABLE）    false: 设置白色背景黑色字体（View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR）
 * @param color:   在设置了android:fitsSystemWindows="true"时，需要更改状态栏背景颜色时，设置color
 * 再将状态栏背景设置为透明色（Color.TRANSPARENT）
 */
fun Activity.setStatusBarByView(dark: Boolean = false, color: Int = Color.TRANSPARENT) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (dark) {
            //黑色背景白色字体
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            //白色背景黑色字体
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }else {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
//      设置padding以及占位符会出现键盘顶不上去输入框问题 所以直接采用5.0之后的Google方法
        window.statusBarColor = Color.BLACK
    }
}
