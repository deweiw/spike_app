package com.dandv.spike.tools

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class ImageProcessingTarget constructor(private val callback: Callback) : Target {

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        // nothing need to do
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        callback.onBitmapFailed(e?.let { it } ?: Exception("on bitmap load failed"))
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        callback.onBitmapLoaded(bitmap)
    }

    interface Callback {
        fun onBitmapFailed(e: Exception)
        fun onBitmapLoaded(bitmap: Bitmap?)
    }
}