package com.android.frameworktool.network

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
const val HOST="http://39.99.210.2:8080/"
inline fun <reified T> Observable<T>.handleByContext(
    context: Context?,
    crossinline success: ((T) -> Unit),
    noinline errorCallback: ((Throwable) -> Boolean)? = null
): Disposable {
    return handle(context as? ApiRequestDisposedWatcher, success, errorCallback)
}

inline fun <reified T> Observable<T>.handle(apiRequestDisposedWatcher: ApiRequestDisposedWatcher? = null,
                                                    crossinline success: ((T) -> Unit),
                                                    noinline errorCallback: ((Throwable) -> Boolean)? = null): Disposable {
    val disposed = this
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            success.invoke(it)
        }, {
            errorCallback?.invoke(it)
            Unit
        })

    disposed?.let { apiRequestDisposedWatcher?.addDisposable(it) }
    return disposed
}

