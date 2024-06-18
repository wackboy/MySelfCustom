package com.example.myselfcustom.ui.slideleftfragment

import com.example.myselfcustom.arch.livedatabus.MessageEvent

interface SlideClickEvent {
    fun clickEvent(): MessageEvent<String>
}