package wealoha.android.framework.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.android.frameworktool.storage.Key
import com.tencent.mmkv.MMKV

class StoreManager {
    val defaultStore: MMKV = MMKV.defaultMMKV()
    val notClearStore: MMKV = MMKV.mmkvWithID("ALOHA_NOT_CLEAR_PREFERENCES")

    companion object {
        val instance = StoreManager()
    }

    fun remove(key: String) {
        defaultStore.remove(key)
    }

    fun clear(): Boolean {
        return defaultStore.clear().commit()
    }
}
//删除数据
open class Entity<T>(open val key: Key<T>) {
    var value: T?
        get() {
            return key.convert.get(key.key, cache())
        }
        set(value) {
            key.convert.set(key.key, cache(), value)
        }

    open fun cache(): MMKV? {
        return StoreManager.instance.defaultStore
    }
}
//卸载APP不删除数据
class NotClearEntity<T>(override val key: Key<T>) : Entity<T>(key) {
    override fun cache(): MMKV? {
        return StoreManager.instance.notClearStore
    }
}