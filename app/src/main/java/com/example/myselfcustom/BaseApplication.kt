package com.example.myselfcustom

import android.app.Application

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        ARouter.init(this)
    }

}