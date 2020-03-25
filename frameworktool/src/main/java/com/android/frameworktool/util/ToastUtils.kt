package com.android.frameworktool.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import android.view.Gravity
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.android.frameworktool.R


object ToastUtils  {


    fun spToastError(context: Context?, resId: Int?, iconResId: Int?, duration: Int = Toast.LENGTH_SHORT) {
        if (!checkTokenIsValid(context) || resId == null) return
        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_common_alert, null)
        val textView = view.findViewById<TextView>(R.id.contentTextView)
        textView.setText(resId)
        val iconView = view.findViewById<ImageView>(R.id.alertIconImageView)
        iconView.setImageResource(iconResId ?: R.mipmap.white_error_icon)
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = duration
        toast.show()
    }

    fun spToastError(context: Context?, text: String?, iconResId: Int? , duration: Int = Toast.LENGTH_SHORT) {
        if (!checkTokenIsValid(context) || text == null) return

        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_common_alert, null)
        val textView = view.findViewById<TextView>(R.id.contentTextView)
        val iconView = view.findViewById<ImageView>(R.id.alertIconImageView)
        iconView.setImageResource(iconResId ?: R.mipmap.white_error_icon)

        textView.text = text
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = duration
        toast.show()
    }

    fun spToastSucceed(context: Context?, resId: Int?, iconResId: Int?, duration: Int = Toast.LENGTH_SHORT) {
        if (!checkTokenIsValid(context) || resId == null) return
        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_common_alert, null)
        val textView = view.findViewById<TextView>(R.id.contentTextView)
        textView.setText(resId)
        val iconView = view.findViewById<ImageView>(R.id.alertIconImageView)
        iconView.setImageResource(iconResId ?: R.mipmap.white_success_icon)
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = duration
        toast.show()
    }

    fun spToastSucceed(context: Context?, text: String?, iconResId: Int? , duration: Int = Toast.LENGTH_SHORT) {
        if (!checkTokenIsValid(context) || text == null) return

        val toast = Toast(context)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_common_alert, null)
        val textView = view.findViewById<TextView>(R.id.contentTextView)
        val iconView = view.findViewById<ImageView>(R.id.alertIconImageView)
        iconView.setImageResource(iconResId ?: R.mipmap.white_success_icon)
        textView.text = text
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = duration
        toast.show()
    }

    fun checkTokenIsValid(context: Context?): Boolean {
        return context is Activity && !context.isFinishing
    }
}