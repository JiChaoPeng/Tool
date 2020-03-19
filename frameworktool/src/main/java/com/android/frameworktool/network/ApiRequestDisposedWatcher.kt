package com.android.frameworktool.network

import android.content.Context
import io.reactivex.disposables.Disposable

interface ApiRequestDisposedWatcher {
    fun addDisposable(disposable: Disposable)
    fun getStartRequestContext(): Context?
}