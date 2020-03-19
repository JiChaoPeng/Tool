package com.android.frameworktool.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.android.frameworktool.R
import com.android.frameworktool.util.appInflate
import com.android.frameworktool.util.dip
import com.android.frameworktool.util.getStatusBarHeight
import com.android.frameworktool.util.onSingleClick
import kotlinx.android.synthetic.main.layout_app_title_bar.view.*

/**
 * Created by Gary
 * on 2017/10/30.
 */
class TitleBarLayout : FrameLayout {

    private var titleText = ""
    private var leftOptionImageRes = R.mipmap.goback_black
    private var backGroundColor = Color.WHITE
    private var rightOptionText = ""
    private var rightOptionImageRes = 0

    var leftOptionEvent: ((View?) -> Unit)? = null
        set(value) {
            field = value
            leftOptionView.onSingleClick(field)
        }

    var rightOptionEvent: ((View?) -> Unit)? = null
        set(value) {
            field = value
            rightOptionView.onSingleClick(field)
            rightImageOptionView.onSingleClick(field)
        }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }

    fun initView(context: Context, attrs: AttributeSet? = null) {

        appInflate(R.layout.layout_app_title_bar, true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val statusBarHeight = context.getStatusBarHeight()
            val layoutParams = titleBarContainer.layoutParams
            layoutParams.height = layoutParams.height + statusBarHeight
            titleBarContainer.layoutParams = layoutParams
            titleBarContainer.setPadding(0, statusBarHeight, 0, 0)
        }

        fillView()
    }

    fun removeStatusHeightForTitleLayout() {
        titleBarContainer.layoutParams.height = context.dip(48)
        titleBarContainer.setPadding(0, 0, 0, 0)
    }

    private fun fillView() {
        titleTextView.text = titleText
        titleTextView.paint.isFakeBoldText = true
        rightOptionView.paint.isFakeBoldText = true
        leftOptionView.setImageResource(leftOptionImageRes)
        titleBarContainer.setBackgroundColor(backGroundColor)
        if (rightOptionImageRes == 0) {
            rightImageOptionView.visibility = View.GONE
            rightOptionView.visibility = View.VISIBLE
            rightOptionView.text = rightOptionText
        } else {
            rightImageOptionView.visibility = View.VISIBLE
            rightOptionView.visibility = View.GONE
            rightImageOptionView.setImageResource(rightOptionImageRes)
        }
    }

    fun setTitle(title: String?) {
        titleTextView.text = title ?: ""
    }

    fun getTitle(): String {
        return titleTextView.text.toString()
    }

    fun setTitleTextColor(color: Int) {
        titleTextView.setTextColor(color)
    }

    fun setTitleTextAlpha(alpha: Float) {
        titleTextView.alpha = alpha
    }

    fun setLeftOptionImageResource(resId: Int) {
        leftOptionView.setImageResource(resId)
    }

    fun setLeftOptionImageDrawable(drawable: Drawable) {
        leftOptionView.setImageDrawable(drawable)
    }

    fun setLeftOptionImageVisible(visible: Boolean) {
        leftOptionView.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    fun setRightOptionImageVisible(visible: Boolean) {
        rightImageOptionView.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    fun setBackGroundColor(color: Int) {
        titleBarContainer.setBackgroundColor(color)
    }

    fun setRightOptionText(
        text: String?,
        color: Int = ContextCompat.getColor(context, R.color.text_black),
        visibility: Int = View.VISIBLE
    ) {
        rightOptionView.text = text ?: ""
        rightOptionView.setTextColor(color)
        rightOptionView.visibility = visibility
    }

    fun setRightOptionImage(resId: Int) {
        rightImageOptionView.visibility = View.VISIBLE
        rightImageOptionView.setImageResource(resId)
    }

}