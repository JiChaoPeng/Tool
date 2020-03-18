package com.android.frameworktool.recycler

import android.view.View
import android.widget.Switch
import androidx.recyclerview.widget.RecyclerView
import com.android.frameworktool.util.onLongClick
import com.android.frameworktool.util.onSingleClick

abstract class BaseRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var model: Any? = null
        set(value) {
            field = value
            config(value)
        }

    open fun config(model: Any?) {}

    open fun config(model: Any?, dateSize: Int) {}

    fun bindViewClickEvent(viewHolderConfig: ViewHolderConfig) {
        itemView.onSingleClick {
            viewHolderConfig.itemClickListener?.invoke(model)
        }

        itemView.onLongClick {
            viewHolderConfig.itemLongClickListener?.invoke(model)
        }

        viewHolderConfig.subviewOnClickListenerMap.forEach { entry ->
            val subView = itemView.findViewById<View>(entry.key)
            val method = entry.value
            subView?.setOnClickListener { method.invoke(model) }
        }
        viewHolderConfig.subviewOnClickPositionListenerMap.forEach { entry ->
            val subView = itemView.findViewById<View>(entry.key)
            val method = entry.value
            subView?.setOnClickListener { method.invoke(model, adapterPosition) }
        }
        viewHolderConfig.subviewOnCheckChangeListenerMap.forEach { entry ->
            val subView = itemView.findViewById<Switch>(entry.key)
            val method = entry.value
            subView?.setOnCheckedChangeListener { buttonView, isChecked ->
                if (buttonView.isPressed) {
                    method.invoke(model, isChecked, adapterPosition) }
            }
        }

        viewHolderConfig.subviewOnLongClickListenerMap.forEach { entry ->
            val subView = itemView.findViewById<View>(entry.key)
            val method = entry.value
            subView?.setOnLongClickListener {
                method.invoke(model)
                true
            }
        }
    }
}