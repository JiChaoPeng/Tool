package com.android.frameworktool.util

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by jichaopeng
 * 2020/3/18
 */
fun loadImage(imageView: ImageView, uri: Uri, width: Int) {
    Glide.with(imageView.context)
        .load(uri)
        .override(width, width)
        .into(imageView)

}

fun loadImage(imageView: ImageView, uri: Uri) {
    Glide.with(imageView.context)
        .load(uri)
        .into(imageView)
}

fun loadImage(imageView: ImageView, path: String, width: Int) {
    Glide.with(imageView.context)
        .load(path)
        .override(width, width)
        .into(imageView)
}

fun loadImage(imageView: ImageView, path: String) {
    Glide.with(imageView.context)
        .load(path)
        .into(imageView)
}