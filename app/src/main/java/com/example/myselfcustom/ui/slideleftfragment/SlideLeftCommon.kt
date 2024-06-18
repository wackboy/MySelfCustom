package com.example.myselfcustom.ui.slideleftfragment

import com.example.myselfcustom.arch.CoroutineActivity
import com.example.myselfcustom.sourcecodelearn.apt.AptFragment
import com.example.myselfcustom.sourcecodelearn.sourceeventbus.EventBusFragment
import com.example.myselfcustom.sourcecodelearn.sourcerxjava.RxjavaLearnActivity
import com.example.myselfcustom.ui.customview.viewclickspan.ClickSpanFragment
import com.example.myselfcustom.ui.customview.viewlike.ThirdFragment
import com.example.myselfcustom.ui.customview.viewrect.FifthFragment
import com.example.myselfcustom.ui.customview.viewscalerv.SecondFragment
import com.example.myselfcustom.ui.customview.viewslidecard.FourthFragment
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.APT_ACTIVITY_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.CLICK_SPAN_FRAGMENT_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.COROUTINE_ACTIVITY_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.EVENTBUS_ACTIVITY_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.FIFTH_FRAGMENT_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.FOURTH_FRAGMENT_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.RXJAVA_LEARN_ACTIVITY_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.SECOND_FRAGMENT_PATH
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon.THIRD_FRAGMENT_PATH

object SlideLeftCommon {

    const val SECOND_FRAGMENT_PATH = "/slide/secondFragment"
    const val THIRD_FRAGMENT_PATH = "/slide/ThirdFragment"
    const val FOURTH_FRAGMENT_PATH = "/slide/FourthFragment"
    const val FIFTH_FRAGMENT_PATH = "/slide/FifthFragment"
    const val OKHTTP_ACTIVITY_PATH = "/slide/OkHttpActivity"
    const val EVENTBUS_ACTIVITY_PATH = "/slide/EventBusActivity"
    const val RXJAVA_LEARN_ACTIVITY_PATH = "/slide/RxjavaLearnActivity"
    const val APT_ACTIVITY_PATH = "/slide/AptActivity"
    const val CLICK_SPAN_FRAGMENT_PATH = "/slide/ClickSpanFragment"
    const val COROUTINE_ACTIVITY_PATH = "/slide/CoroutineActivity"

}


val arrayPairInfo = listOf(
    Pair("放缩RV效果", SecondFragment::class.java),
    Pair("点赞效果", ThirdFragment::class.java),
    Pair("探探划卡", FourthFragment::class.java),
    Pair("自定义文本框", FifthFragment::class.java),
    Pair("EventBus", EventBusFragment::class.java),
    Pair("Rxjava", RxjavaLearnActivity::class.java),
    Pair("Apt", AptFragment::class.java),
    Pair("ClickSpanConflict", ClickSpanFragment::class.java),
    Pair("协程相关", CoroutineActivity::class.java)
)

val routePair = listOf(
    Pair(SecondFragment::class.simpleName, SECOND_FRAGMENT_PATH),
    Pair(ThirdFragment::class.simpleName, THIRD_FRAGMENT_PATH),
    Pair(FourthFragment::class.simpleName, FOURTH_FRAGMENT_PATH),
    Pair(FifthFragment::class.simpleName, FIFTH_FRAGMENT_PATH),
    Pair(EventBusFragment::class.simpleName, EVENTBUS_ACTIVITY_PATH),
    Pair(RxjavaLearnActivity::class.simpleName, RXJAVA_LEARN_ACTIVITY_PATH),
    Pair(AptFragment::class.simpleName, APT_ACTIVITY_PATH),
    Pair(ClickSpanFragment::class.simpleName, CLICK_SPAN_FRAGMENT_PATH),
    Pair(CoroutineActivity::class.simpleName, COROUTINE_ACTIVITY_PATH),
)
