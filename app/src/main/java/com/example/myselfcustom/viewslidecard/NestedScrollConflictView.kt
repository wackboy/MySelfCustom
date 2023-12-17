package com.example.myselfcustom.viewslidecard

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewParent
import androidx.core.view.doOnPreDraw
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class NestedScrollConflictView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : NestedScrollView(context, attrs) {

    private var conflictView: RecyclerView? = null
    private var startX: Float = 0f
    private var startY: Float = 0f

    init {
        this.doOnPreDraw {
            var currentView: ViewParent? = parent
            while (currentView != null) {
                if (currentView is RecyclerView) {
                    conflictView = currentView
                    break
                } else currentView = currentView.parent
            }
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
                conflictView?.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = abs(ev.x - startX)
                val deltaY = abs(ev.y - startY)
                if (deltaY > deltaX) {
                    conflictView?.requestDisallowInterceptTouchEvent(true)
                } else {
                    conflictView?.requestDisallowInterceptTouchEvent(false)
                }
            }
            else -> {
                conflictView?.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.onTouchEvent(ev)
    }
}