package com.example.myselfcustom.ui.customview.viewscalerv

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.utils.ScreenUtils

class UIScaleRVHelper(
    private val context: Context,
    private val recyclerView: RecyclerView
) {

    private var mGalleryWidth: Int = 0
    private var mGalleryMove = 0
    private var mGalleryScaleY = 0.3f
    private var mGalleryTranslation = 0
    private var mGalleryMiddle = 0
    private var mTotalContentX = 0

    init {
        mGalleryWidth = ScreenUtils.dp2px(context, 327f)
        mGalleryMiddle = ScreenUtils.getRelScreenWidth() / 2    // 屏幕宽度的一半
        mGalleryMove = mGalleryWidth / 2    // item宽度的一半
        mGalleryTranslation =
            (mGalleryMove * mGalleryScaleY - ScreenUtils.dp2px(context, 12f)).toInt()
        initView()
        initRvFoot()
    }


    private fun initView() {
        val contentManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = contentManager
        recyclerView.addItemDecoration(ContentItemDecoration(context))
//        val snapHelperContent = PagerSnapHelper()
//        snapHelperContent.attachToRecyclerView(recyclerView)
        initRvContentScroll()
    }

    private fun initRvContentScroll() {
        recyclerView.addOnScrollListener (object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mTotalContentX += dx
                if (recyclerView.childCount == 1) {
                    val view = recyclerView.getChildAt(0)
                    view.scaleX = 1f
                    view.scaleY = 1f
                } else {
                    for (i in 0 until recyclerView.childCount) {
                        val view = recyclerView.getChildAt(i)
                        var rate = 0f
                        val left = (view.left + view.right) / 2
                        if (left <= mGalleryMiddle) {
                            rate = if (left < mGalleryMiddle - mGalleryMove) {
                                1f
                            } else {
                                (mGalleryMiddle - left) * 1f / mGalleryMove
                            }
                            view.scaleX = 1 - rate * mGalleryScaleY
                            view.scaleY = 1 - rate * mGalleryScaleY
                            view.translationX = rate * mGalleryTranslation
                        } else {
                            rate = if (left > mGalleryMiddle + mGalleryMove) {
                                0f
                            } else {
                                (mGalleryMiddle + mGalleryMove - left) * 1f / mGalleryMove
                            }
                            view.scaleX = 1 - mGalleryScaleY + rate * mGalleryScaleY
                            view.scaleY = 1 - mGalleryScaleY + rate * mGalleryScaleY
                            view.translationX = (rate - 1) * mGalleryTranslation
                        }
                    }
                }
            }
        })
    }

    private fun initRvFoot() {
        val list = arrayListOf("2", "3", "4", "5", "6", "7", "8", "9")
        val contentAdapter = ContentAdapter(list)
        recyclerView.adapter = contentAdapter
    }

}