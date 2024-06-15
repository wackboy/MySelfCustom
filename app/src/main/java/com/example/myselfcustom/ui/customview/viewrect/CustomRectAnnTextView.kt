package com.example.myselfcustom.ui.customview.viewrect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myselfcustom.utils.dp

class CustomRectAnnTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttr) {

    private var shaderPath = Path()
    private var rightPath = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
//        color = Color.LTGRAY
//        style = Paint.Style.STROKE // 描边
//        strokeWidth = 20.toFloat()
//        // 画笔连接处使用的样式
//        strokeJoin = Paint.Join.ROUND
    }

    init {
        setWillNotDraw(false)
    }

    private fun initLeftPath() {
        val triangleWidth = 6.dp()
        val triangleHeight = 16.dp()
        val radius = 16.dp()
        val leftRectWidth = 100.dp()
        shaderPath.reset()
        shaderPath.moveTo(radius, 0f)
        // 最上面的那条线
        shaderPath.lineTo(leftRectWidth, 0f)
        // 最右边的线+三角形
        shaderPath.lineTo(leftRectWidth, (height - triangleHeight) / 2)
        shaderPath.lineTo(leftRectWidth + triangleWidth, height / 2f)
        shaderPath.lineTo(leftRectWidth, height / 2f + triangleHeight / 2f)
        // 剩下的线+圆角
        shaderPath.lineTo(leftRectWidth, height.toFloat())
        shaderPath.lineTo(radius, height.toFloat())
        shaderPath.arcTo(0f, height - radius, radius, height.toFloat(), 90f, 90f, false)
        shaderPath.lineTo(0f, radius)
        shaderPath.arcTo(0f, 0f, radius, radius, 180f, 90f, false)
    }

    private fun initRightPath() {
        val radius = 16.dp()
        val leftRectWidth = 100.dp()
        rightPath.reset()
        rightPath.moveTo(leftRectWidth, 0f)
        rightPath.lineTo(width.toFloat() - radius, 0f)
        rightPath.arcTo(
            width.toFloat() - radius,
            0f,
            width.toFloat(),
            radius,
            -90f,
            90f,
            false
        )
        rightPath.lineTo(width.toFloat(), height.toFloat() - radius)
        rightPath.arcTo(width.toFloat() - radius, height.toFloat() - radius, width.toFloat(), height.toFloat(), 0f, 90f, false)
        rightPath.lineTo(100.dp(), height.toFloat())
        rightPath.close()
    }

    private fun createShader(): Shader {
        val startColor = Color.parseColor("#FEDD83")
        val endColor = Color.parseColor("#F2CA5C")
        return LinearGradient(
            0f, 0f, 106.dp(), 0f, startColor, endColor, Shader.TileMode.CLAMP
        )
    }

    private fun createShader2(): Shader {
        val startColor = Color.parseColor("#FEDD83")
        val endColor = Color.parseColor("#FFFFAA")
        return LinearGradient(
            0f, 0f, 106.dp(), 0f, startColor, endColor, Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas) {
        initLeftPath()
        initRightPath()
        paint.shader = createShader2()
        canvas.drawPath(rightPath, paint)
        val shader = createShader()
        paint.shader = shader
        canvas.drawPath(shaderPath, paint)
        paint.shader = null
    }


}