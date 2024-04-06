package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import com.example.myselfcustom.retorfitutil.ApiResponse
import com.example.myselfcustom.retorfitutil.BaseRepository
import com.example.myselfcustom.utils.ServiceCreator

class HomePageRepo : BaseRepository() {

    private val service = ServiceCreator.create<IHomePage>()

    fun getBannerInfo(): LiveData<ApiResponse<List<Banner>>> {
        // todo 支持sequence的网络请求，
        return simpleLiveData {
            executeHttp {
                service.getBannerInfo()
            }
        }
    }
}