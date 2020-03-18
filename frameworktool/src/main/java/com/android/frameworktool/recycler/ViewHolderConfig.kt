package com.android.frameworktool.recycler

/**
 * Created by chen on 2019/3/18
 */
class ViewHolderConfig {
    var itemClickListener: ((model: Any?) -> Unit)? = null
    var itemLongClickListener: ((model: Any?) -> Unit)? = null
    var subviewOnClickListenerMap = mutableMapOf<Int, ((model: Any?) -> Unit)>()
    var subviewOnCheckChangeListenerMap = mutableMapOf<Int, ((model: Any?, checked: Boolean, position: Int) -> Unit)>()
    var subviewOnClickPositionListenerMap = mutableMapOf<Int, ((model: Any?, position: Int) -> Unit)>()
    var subviewOnLongClickListenerMap = mutableMapOf<Int, ((model: Any?) -> Unit)>()
}