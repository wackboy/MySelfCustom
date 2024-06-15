package com.example.myselfcustom.ui.customview.viewslidecard

class CardMeta(
    val resId: Int,
    val index: Int
) {

    var temp: Int = index - 5
    var times: Int = 0
        get() {
            return if (temp > 1) 7
            else 10
        }
        set(value) {
            field = value + 1
        }
}