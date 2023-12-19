package com.example.myselfcustom.utils

import android.content.res.Resources

fun Int.dp(): Float {
    return (this * Resources.getSystem().displayMetrics.density + 0.5f)
}

fun Int.sp(): Float {
    return (this * Resources.getSystem().displayMetrics.scaledDensity + 0.5f)
}