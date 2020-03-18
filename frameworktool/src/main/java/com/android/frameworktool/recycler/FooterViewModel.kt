package com.android.frameworktool.recycler

import android.graphics.Color

data class FooterViewModel(var showProgress: Boolean = true,
                           var showText: Boolean = false,
                           var text: String? = "",
                           var textColor: Int = Color.BLACK) {

    fun modifyParam(showProgress: Boolean = true,
                    showText: Boolean = false,
                    text: String? = "",
                    textColor: Int = Color.BLACK) {
        this.showProgress = showProgress
        this.showText = showText
        this.text = text
        this.textColor = textColor
    }
}