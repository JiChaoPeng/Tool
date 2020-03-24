package com.android.frameworktool.storage

import com.google.gson.Gson
import com.tencent.mmkv.MMKV

interface Convert<T> {
    fun set(key: String, cache: MMKV?, value: T?)
    fun get(key: String, cache: MMKV?): T?
}

class ModelConvert<T>(val type: Class<T>) : Convert<T> {
    override fun set(key: String, cache: MMKV?, value: T?) {
        cache?.encode(key, Gson().toJson(value))
    }

    override fun get(key: String, cache: MMKV?): T? {
        return Gson().fromJson(cache?.decodeString(key), type)
    }

}

class StringConvert : Convert<String> {
    override fun set(key: String, cache: MMKV?, value: String?) {
        cache?.putString(key, value)
    }

    override fun get(key: String, cache: MMKV?): String? {
        return cache?.getString(key, "")
    }

}

class IntConvert(private val defaultValue: Int = 0) : Convert<Int> {
    override fun set(key: String, cache: MMKV?, value: Int?) {
        if (value != null) {
            cache?.putInt(key, value)
        } else {
            cache?.removeValueForKey(key)
        }
    }

    override fun get(key: String, cache: MMKV?): Int? {
        return cache?.getInt(key, defaultValue)
    }

}

class BooleanConvert(private val defaultValue: Boolean = false) : Convert<Boolean> {
    override fun set(key: String, cache: MMKV?, value: Boolean?) {
        if (value != null) {
            cache?.putBoolean(key, value)
        } else {
            cache?.removeValueForKey(key)
        }
    }

    override fun get(key: String, cache: MMKV?): Boolean? {
        return cache?.getBoolean(key, defaultValue)
    }

}

class LongConvert(private val defaultValue: Long = 0L) : Convert<Long> {
    override fun set(key: String, cache: MMKV?, value: Long?) {
        if (value != null) {
            cache?.putLong(key, value)
        } else {
            cache?.removeValueForKey(key)
        }
    }

    override fun get(key: String, cache: MMKV?): Long? {
        return cache?.getLong(key, defaultValue)
    }

}