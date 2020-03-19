package com.android.frameworktool.network

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by Gary
 * on 16/6/22.
 */
class ResultDataHandler<T> : Function<Result<T>, Observable<T>> {
    override fun apply(result: Result<T>): Observable<T>? {
        return Observable.create { subscribe ->

        }
    }

}