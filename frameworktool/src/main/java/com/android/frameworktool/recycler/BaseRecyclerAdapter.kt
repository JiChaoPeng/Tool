package com.android.frameworktool.recycler

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerViewHolder>() {
    val viewHolderConfig = ViewHolderConfig()
    val typeList = mutableListOf<Class<out Any>>()
    val modelList = mutableListOf<Any>()

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        if (position >= 0 && position < modelList.count()) {
            holder.model = modelList[position]
            holder.config(modelList[position], modelList.count())
        } else {
            throw IndexOutOfBoundsException("BaseRecyclerAdapter onBindViewHolder IndexOutOfBounds " +
                    "position:$position  count:${modelList.count()} holder:${holder::class.java.simpleName}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (typeList.isEmpty() || modelList.isEmpty()) {
            return 0
        } else {
            var type = -1
            val modelCount = modelList.count()
            typeList.forEach {
                if (modelCount > position && it.isInstance(modelList[position])) {
                    type = typeList.indexOf(it)
                    return@forEach
                }
            }

            if (type == -1) {
                error("not find model class int type list:${modelList[position]}")
            }
            return type
        }
    }

    override fun getItemCount(): Int = modelList.count()

    open fun addModels(list: List<Any>?) {
        list ?: return
        if (modelList.lastOrNull() is FooterViewModel) {
            modelList.addAll(modelList.lastIndex, list)
        } else {
            modelList.addAll(list)
        }
    }

    open fun addModel(model: Any) {
        if (modelList.lastOrNull() is FooterViewModel) {
            modelList.add(modelList.lastIndex, model)
        } else {
            modelList.add(model)
        }
    }

    fun removeFootView() {
        if (modelList.lastOrNull() is FooterViewModel) {
            modelList.remove(modelList.last())
        }
    }

    fun getFooterViewModelOrNull(): FooterViewModel? {
        val footerViewModel = modelList.lastOrNull()
        return if (footerViewModel is FooterViewModel) {
            footerViewModel
        } else {
            null
        }
    }

    fun modelListCount(): Int {
        return if (modelList.lastOrNull() is FooterViewModel) modelList.count() - 1 else modelList.count()
    }

}