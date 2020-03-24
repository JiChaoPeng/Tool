package com.android.frameworktool.recycler

class ViewHolderConfig {
    var itemClickListener: ((model: Any?) -> Unit)? = null
    var itemLongClickListener: ((model: Any?) -> Unit)? = null
    var subviewOnClickListenerMap = mutableMapOf<Int, ((model: Any?) -> Unit)>()
    var subviewOnCheckChangeListenerMap = mutableMapOf<Int, ((model: Any?, checked: Boolean, position: Int) -> Unit)>()
    var subviewOnClickPositionListenerMap = mutableMapOf<Int, ((model: Any?, position: Int) -> Unit)>()
    var subviewOnLongClickListenerMap = mutableMapOf<Int, ((model: Any?) -> Unit)>()
}