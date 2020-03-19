package com.android.frameworktool.network

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * Created by jichaopeng
 * 2020/3/19
 */

inline fun <reified T> Observable<Result<T>>.handleByContext(
    context: Context?,
    crossinline success: ((T) -> Unit),
    noinline errorCallback: ((Throwable) -> Boolean)? = null
): Disposable {
    return handle(context as? ApiRequestDisposedWatcher, success, errorCallback)
}

inline fun <reified T> Observable<Result<T>>.handle(
    apiRequestDisposedWatcher: ApiRequestDisposedWatcher? = null,
    crossinline success: ((T) -> Unit),
    noinline errorCallback: ((Throwable) -> Boolean)? = null
): Disposable {
    val disposed = flatMap(ResultDataHandler())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            success.invoke(it)
        }, {
            Unit
        })

    disposed?.let { apiRequestDisposedWatcher?.addDisposable(it) }
    return disposed
}