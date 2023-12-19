package com.example.myselfcustom.viewrect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.example.myselfcustom.R
import com.example.myselfcustom.utils.sp
import kotlin.math.roundToInt

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private lateinit var staticLayout: StaticLayout
    private var hasExtraLine = false
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 13.sp()
    }

    var text: CharSequence = ""
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
            }
        }

    init {
        setWillNotDraw(false)
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomRectAnnTextView)
            try {
                if (ta.hasValue(R.styleable.CustomRectAnnTextView_android_text)) {
                    text = ta.getText(R.styleable.CustomRectAnnTextView_android_text)
                }
                if (ta.hasValue(R.styleable.CustomRectAnnTextView_android_textColor)) {
                    val textColor = ta.getColor(
                        R.styleable.CustomRectAnnTextView_android_textColor,
                        Color.BLACK
                    )
                    textPaint.color = textColor
                }

                if (ta.hasValue(R.styleable.CustomRectAnnTextView_android_textSize)) {
                    val textSize = ta.getDimensionPixelSize(
                        R.styleable.CustomRectAnnTextView_android_textSize,
                        13.sp().roundToInt()
                    )
                    textPaint.textSize = textSize.toFloat()
                }

            } finally {
                ta.recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        // TextView底层就是这个staticLayout
        staticLayout = StaticLayout.Builder
            .obtain(text, 0, text.length, textPaint, width)
            .build()

        if (childCount > 0) {
            val lastLineWidth = staticLayout.getLineWidth(staticLayout.lineCount - 1)
            val childView = getChildAt(0)
            if (childView.visibility == View.GONE) {
                // child view invisible, same as non view
                hasExtraLine = false
                setMeasuredDimension(
                    width + paddingStart + paddingEnd,
                    staticLayout.height + paddingTop + paddingBottom
                    )
                return
            }
            // measure child view
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val childWidth = childView.measuredWidth
            val childViewLp = childView.layoutParams as MarginLayoutParams
            if (lastLineWidth + childWidth + childViewLp.marginStart + childViewLp.marginEnd > width) {
                // cannot set on one line
                hasExtraLine = true
                setMeasuredDimension(
                    width + paddingStart + paddingEnd,
                    staticLayout.height + paddingTop + paddingBottom
                            // special text on nextLine
                            + childView.measuredHeight + childViewLp.topMargin + childViewLp.bottomMargin
                    )
            } else {
                // can set on one line
                hasExtraLine = false
                setMeasuredDimension(width + paddingStart + paddingEnd,
                    staticLayout.height + paddingTop + paddingBottom)
            }
        } else {
            // non child view
            hasExtraLine = false
            setMeasuredDimension(width + paddingStart + paddingEnd,
                staticLayout.height + paddingTop + paddingBottom)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (childCount <= 0 || getChildAt(0).visibility == View.GONE) {
            return
        }
        val childView = getChildAt(0)
        if (hasExtraLine) {
            val childViewLp = childView.layoutParams as MarginLayoutParams
            childView.layout(
                paddingStart + childViewLp.marginStart,
                staticLayout.height + childViewLp.topMargin,
                childView.measuredWidth + paddingStart + childViewLp.marginStart,
                staticLayout.height + childView.measuredHeight + childViewLp.topMargin
            )
        } else {
            val childViewLp = childView.layoutParams as MarginLayoutParams
            val childViewLeft = staticLayout.getLineWidth(staticLayout.lineCount - 1)
                .roundToInt() + paddingStart + childViewLp.marginStart
            val childViewTop =
                staticLayout.height - childView.measuredHeight + childViewLp.topMargin
            childView.layout(
                childViewLeft,
                childViewTop,
                childViewLeft + childView.measuredWidth,
                childViewTop + childView.measuredHeight
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        staticLayout.draw(canvas)
    }

}















