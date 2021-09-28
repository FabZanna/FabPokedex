package com.fabulouszanna.fabpokedex.core.util

import android.content.res.Resources
import android.view.View

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}