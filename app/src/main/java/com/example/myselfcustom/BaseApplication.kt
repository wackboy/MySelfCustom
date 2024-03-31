package com.example.myselfcustom

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }

}