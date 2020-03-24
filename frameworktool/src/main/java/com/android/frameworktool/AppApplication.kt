package com.android.frameworktool

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 * 2020/3/24
 */
class AppApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}