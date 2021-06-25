package com.example.solitarecardview

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.app.Activity
import android.content.Context
import androidx.constraintlayout.widget.ConstraintSet

val colors : Array<Int> = arrayOf(
    "#f44336",
    "#004D40",
    "#FFD600",
    "#00C853",
    "#6200EA"
).map {
    Color.parseColor(it)
}.toTypedArray()
val backColor : Int = Color.parseColor("#BDBDBD")
val strokeFactor : Float = 90f
val cardWFactor : Float = 8.9f
val cardHFactor : Float = 3.9f
val delay : Long = 20
val parts : Int = 3
val scGap : Float = 0.02f / parts
val cards : Int = 3


fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, (this - i * n.inverse()))
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n

fun Canvas.drawSolitareCard(scale : Float, w : Float, h : Float, paint : Paint) {
    val cardW : Float = Math.min(w, h) / cardWFactor
    val cardH : Float = Math.min(w, h) / cardHFactor
    val sc1 : Float = scale.divideScale(0, parts)
    val sc2 : Float = scale.divideScale(1, parts)
    val sc3 : Float = scale.divideScale(2, parts)
    val gap : Float = cardH / cards
    save()
    for (i in 0..(cards - 1)) {
        save()
        translate(0f, -gap * (6 - i) * sc2 - (h / 2 + cardH) * sc3)
        drawLine(
            -cardW / 2,
            -cardH / 2,
            -cardW / 2 + cardW * sc1,
            -cardH / 2, paint
        )
        drawRect(
            RectF(
                -cardW / 2,
                -cardH / 2,
                cardW / 2,
                -cardH / 2 + cardH * sc1
            ),
            paint
        )
        restore()
    }
    restore()

}

fun Canvas.drawSCNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = colors[i]
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    drawSolitareCard(scale, w, h, paint)
}

class SolitareCardView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}
