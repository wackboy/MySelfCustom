package com.example.myselfcustom

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Mesh
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import java.lang.Integer.min

class LikeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        this.isAntiAlias = true
        this.textSize = 200f
    }

    private val textRect0 = Rect(300, 100, 800, 300)
    private val textRect1 = Rect(300, 300, 800, 500)
    private val textRect2 = Rect(300, 500, 800, 700)
    private var nextNumberAlpha: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    private var currentNumberAlpha: Int = 255
        set(value) {
            field = value
            invalidate()
        }

    private var offsetPercent = 0f
        set(value) {
            field = value
            invalidate()
        }

    // FontMetrics：获取字体的度量信息，包括字体的基线、上坡度、下坡度等。这些信息对于文本的精确定位和绘制非常有用。
    private val fontMetrics: FontMetrics = paint.fontMetrics
    private var currentNumber = 99
    private var nextNumber = 0
    private var motionLess = currentNumber.toString()
    private var currentMotion = ""
    private var nextMotion = ""
    private var nextNumberAttr = 255

    private val animator: ObjectAnimator by lazy {
        val nextNumberAlphaAnimator = PropertyValuesHolder.ofInt("nextNumberAlpha", 0, nextNumberAttr)
        val offsetPercentAnimator = PropertyValuesHolder.ofFloat("offsetPercent", 0f, 1f)
        val currentNumberAlphaAnimator = PropertyValuesHolder.ofInt("currentNumberAlpha", 255, 0)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            this,
            nextNumberAlphaAnimator,
            offsetPercentAnimator,
            currentNumberAlphaAnimator
        ).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
            addListener(
                doOnEnd {
                    currentNumber = nextNumber
                }
            )
        }
        animator
    }

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.LikeView)
        nextNumberAttr = typeArray.getInt(R.styleable.LikeView_nextNumberAlpha, 255)
        typeArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, hSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.alpha = 255
        paint.color = Color.LTGRAY
        canvas.drawRect(textRect0, paint)

        paint.color = Color.RED
        canvas.drawRect(textRect1, paint)

        paint.color = Color.GREEN
        canvas.drawRect(textRect2, paint)

        paint.color = Color.BLACK

        if (motionLess.isNotEmpty()) {
            drawText(canvas, motionLess, textRect1, 0f)
        }
        if (nextMotion.isEmpty() || currentMotion.isEmpty()) {
            return
        }

        val textHorizontalOffset =
            if (motionLess.isNotEmpty()) paint.measureText(motionLess) else 0f
        if (nextNumber > currentNumber) {
            paint.alpha = currentNumberAlpha
            drawText(canvas, currentMotion, textRect1, textHorizontalOffset, -offsetPercent)
            paint.alpha = nextNumberAlpha
            drawText(canvas, nextMotion, textRect2, textHorizontalOffset, offsetPercent)
        } else {
            paint.alpha = nextNumberAlpha
            drawText(canvas, nextMotion, textRect0, textHorizontalOffset, offsetPercent)
            paint.alpha = currentNumberAlpha
            drawText(canvas, currentMotion, textRect1, textHorizontalOffset, -offsetPercent)
        }
    }

    private fun drawText(
        canvas: Canvas,
        motion: String,
        rect: Rect,
        offset: Float = 0f,
        offsetPercent: Float = 0f
    ) {
        canvas.drawText(
            motion,
            rect.left.toFloat() + offset,
            rect.top + (rect.bottom - rect.top) / 2f - (fontMetrics.bottom + fontMetrics.top) / 2f + offsetPercent * 200,
            paint
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    fun plus() {
        if (currentNumber == Int.MAX_VALUE) return
        nextNumber = currentNumber + 1
        processText(findEqualsStringIndex())
        if (animator.isRunning) return
        animator.start()
    }

    fun minus() {
        if (currentNumber == 0) return
        nextNumber = currentNumber - 1
        processText(findEqualsStringIndex())
        if (animator.isRunning) return
        animator.start()
    }

    private fun findEqualsStringIndex(): Int {
        var equalIndex = -1
        val nextNumberStr = nextNumber.toString()
        val currentNumberStr = currentNumber.toString()
        val endIndex = min(currentNumberStr.length, nextNumberStr.length)
        for (index in 0..endIndex) {
            if (nextNumberStr[index] != currentNumberStr[index]) break
            equalIndex = index
        }
        return equalIndex
    }

    private fun processText(index: Int) {
        val currentNumberStr = currentNumber.toString()
        val nextNumberStr = nextNumber.toString()
        if (index == -1) {
            motionLess = ""
            currentMotion = currentNumberStr
            nextMotion = nextNumberStr
        } else {
            motionLess = currentNumberStr.substring(0, index + 1)
            currentMotion = currentNumberStr.substring(index + 1)
            nextMotion = nextNumberStr.substring(index + 1)
        }
    }

}
















