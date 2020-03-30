package com.android.frameworktool.util

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by jichaopeng
 * 2020/3/18
 */
fun getImageOptions():RequestOptions{
   return RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

}
fun loadImage(imageView: ImageView, uri: Uri, width: Int) {
    Glide.with(imageView.context)
        .load(uri)
        .apply(getImageOptions())
        .override(width, width)
        .into(imageView)

}

fun loadImage(imageView: ImageView, uri: Uri) {
    Glide.with(imageView.context)
        .load(uri)
        .apply(getImageOptions())
        .into(imageView)
}

fun loadImage(imageView: ImageView, path: String, width: Int) {
    Glide.with(imageView.context)
        .load(path)
        .apply(getImageOptions())
        .override(width, width)
        .into(imageView)
}

fun loadImage(imageView: ImageView, path: String) {
    Glide.with(imageView.context)
        .load(path)
        .apply(getImageOptions())
        .into(imageView)
}