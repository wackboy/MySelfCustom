package com.example.myselfcustom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntDef
import kotlin.math.min
import kotlin.properties.Delegates


class SlidingIndicatorBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private var barHeight by Delegates.notNull<Int>()
    private var barColor by Delegates.notNull<Int>()
    private var bendingHeight by Delegates.notNull<Int>()
    private var bendingRatio: Float
    private var bendingDirection by Delegates.notNull<Int>()
    private var barPaint: Paint
    private var circlePaint: Paint
    private  var rectF: RectF = RectF(0f, 0f, 0f, 0f)
    private var path = Path()

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingIndicatorBar)
        barHeight = typeArray.getDimensionPixelSize(R.styleable.SlidingIndicatorBar_barHeight, 20)
        barColor = typeArray.getColor(R.styleable.SlidingIndicatorBar_barColor, Color.LTGRAY)
        bendingHeight = typeArray.getDimensionPixelSize(R.styleable.SlidingIndicatorBar_bendingHeight, 60)
        bendingRatio = typeArray.getFloat(R.styleable.SlidingIndicatorBar_bendingRatio, 0f)
        bendingDirection = typeArray.getInt(R.styleable.SlidingIndicatorBar_bendingDirection, Direction.UP)
        typeArray.recycle()
        // 抗锯齿
        barPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = barColor
            style = Paint.Style.STROKE // 描边
            strokeWidth = barHeight.toFloat()
            // 画笔连接处使用的样式
            strokeJoin = Paint.Join.ROUND
        }
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = barColor
            style = Paint.Style.FILL
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = if (hMode == MeasureSpec.EXACTLY) {
            hSize
        } else {
            if (bendingRatio <= 0) {
                barHeight + paddingTop + paddingBottom
            } else {
                val h = (barHeight + bendingHeight * bendingRatio).toInt()
                min(h + paddingBottom + paddingTop, hSize)
            }
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val radius = barHeight / 2f
        if (bendingRatio <= 0) {
            rectF.set(paddingLeft.toFloat(), paddingTop.toFloat(),
                (width - paddingRight).toFloat(), (paddingTop + barHeight).toFloat()
            )
            barPaint.style = Paint.Style.FILL
            canvas.drawRoundRect(rectF, radius, radius, barPaint)
        } else {
            if (bendingDirection == Direction.UP) {
                drawArrowUp(canvas, radius)
            } else {
                drawArrowDown(canvas, radius)
            }
        }
    }

    private fun drawArrowUp(canvas: Canvas, radius: Float) {
        path.reset()
        path.moveTo(paddingLeft + radius, paddingTop + radius)
        val x = paddingLeft + (width - paddingLeft - paddingRight) / 2f
        val y = paddingTop + barHeight / 2f + bendingHeight * bendingRatio
        path.lineTo(x, y)
        path.lineTo(width - paddingRight - radius, paddingTop + radius)
        barPaint.style = Paint.Style.STROKE
        canvas.drawPath(path, barPaint)
        canvas.drawCircle(paddingLeft + radius, paddingTop + radius, radius, circlePaint)
        canvas.drawCircle(width - paddingRight - radius, paddingTop + radius, radius, circlePaint)
    }

    private fun drawArrowDown(canvas: Canvas, radius: Float) {
        path.reset()
        path.moveTo(paddingLeft + radius, height - paddingBottom - radius)
        val x = paddingLeft + (width - paddingLeft - paddingRight) / 2f
        val y = paddingTop + barHeight / 2f
        path.lineTo(x, y)
        path.lineTo(width - paddingRight - radius, height - paddingBottom - radius)
        barPaint.style = Paint.Style.STROKE
        canvas.drawPath(path, barPaint)
        canvas.drawCircle(paddingLeft + radius, height - paddingBottom - radius, radius, circlePaint)
        canvas.drawCircle(width - paddingRight - radius, height - paddingBottom - radius, radius, circlePaint)
    }

    fun setBindingHeight(height: Int) {
        this.bendingHeight = height
        requestLayout()
    }

    fun setBendingRatio(ratio: Float) {
        this.bendingRatio = ratio
        requestLayout()
    }

}

@Retention(AnnotationRetention.SOURCE)
@IntDef(Direction.DOWN, Direction.UP)
annotation class Direction {
    companion object {
        const val DOWN: Int = 0
        const val UP: Int = 1
    }
}