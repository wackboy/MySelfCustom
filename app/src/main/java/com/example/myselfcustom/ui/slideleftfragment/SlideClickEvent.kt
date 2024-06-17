package com.example.myselfcustom.ui.slideleftfragment

import com.example.myselfcustom.arch.livedatabus.LiveDataEvent

interface SlideClickEvent {
    fun clickEvent(): LiveDataEvent<String>
}