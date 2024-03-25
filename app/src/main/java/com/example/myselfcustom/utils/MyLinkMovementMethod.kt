package com.example.myselfcustom.utils

import android.text.Layout
import android.text.Spannable
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.widget.TextView


class MyLinkMovementMethod : View.OnTouchListener {

    fun getInstance(): MyLinkMovementMethod? {
        if (sInstance == null) sInstance = MyLinkMovementMethod()
        return sInstance
    }

    private var sInstance: MyLinkMovementMethod? = null

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        var ret = false
        val text = (v as TextView).text
        val stext = Spannable.Factory.getInstance().newSpannable(text)
        val widget = v
        val action = event.action
        if (action == MotionEvent.ACTION_UP ||
            action == MotionEvent.ACTION_DOWN
        ) {
            var x = event.x.toInt()
            var y = event.y.toInt()
            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop
            x += widget.scrollX
            y += widget.scrollY
            val layout: Layout = widget.layout
            val line: Int = layout.getLineForVertical(y)
            val off: Int = layout.getOffsetForHorizontal(line, x.toFloat())
            val link = stext.getSpans(
                off, off,
                ClickableSpan::class.java
            )
            if (link.size != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    link[0].onClick(widget)
                }
                ret = true
            }
        }
        return ret
    }


}