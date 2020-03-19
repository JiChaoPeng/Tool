package com.android.frameworktool.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by jichaopeng
 * 2020/3/18
 */

fun ViewGroup.appInflate(@LayoutRes resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId, this, attachToRoot)
}

class SingleOnClickListener(private val listener: ((v: View?) -> Unit)?) : View.OnClickListener {
    private var lastTime: Long = 0L
    private val interval: Long = 300
    override fun onClick(v: View?) {
        val curTime = System.currentTimeMillis()
        if (curTime - lastTime > interval) {
            listener?.invoke(v)
            lastTime = curTime
        }
    }
}

class LongClickListener(private val listener: ((v: View?) -> Unit)?) : View.OnLongClickListener {
    override fun onLongClick(v: View?): Boolean {
        listener?.invoke(v)
        return true
    }
}

fun View.onSingleClick(listener: ((v: View?) -> Unit)?) {
    setOnClickListener(SingleOnClickListener(listener))
}

fun View.onLongClick(listener: ((v: View?) -> Unit)?) {
    setOnLongClickListener(LongClickListener(listener))
}
