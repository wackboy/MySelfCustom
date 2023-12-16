package com.example.myselfcustom.viewscalerv

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfcustom.databinding.ActivitySecondBinding
import com.example.myselfcustom.utils.ScreenUtils

class SecondActivity : AppCompatActivity() {

    private var mGalleryWidth: Int = 0
    private var mGalleryMove = 0
    private var mGalleryScaleY = 0.3f
    private var mGalleryTranslation = 0
    private var mGalleryMiddle = 0
    private var mTotalContentX = 0
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        mGalleryWidth = ScreenUtils.dp2px(this, 327f)
        mGalleryMiddle = ScreenUtils.getRelScreenWidth() / 2
        mGalleryMove = mGalleryWidth / 2
        mGalleryTranslation =
            (mGalleryMove * mGalleryScaleY - ScreenUtils.dp2px(this, 12f)).toInt()
        initView()
        initRvFoot()
    }

    private fun initView() {
        val contentManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvContent.layoutManager = contentManager
        binding.rvContent.addItemDecoration(ContentItemDecoration(this))
//        val snapHelperContent = PagerSnapHelper()
//        snapHelperContent.attachToRecyclerView(binding.rvContent)
        initRvContentScroll()
    }

    private fun initRvContentScroll() {
        binding.rvContent.addOnScrollListener (object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mTotalContentX += dx
                if (binding.rvContent.childCount == 1) {
                    val view = binding.rvContent.getChildAt(0)
                    view.scaleX = 1f
                    view.scaleY = 1f
                } else {
                    for (i in 0 until binding.rvContent.childCount) {
                        val view = binding.rvContent.getChildAt(i)
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
        binding.rvContent.adapter = contentAdapter
    }


}









