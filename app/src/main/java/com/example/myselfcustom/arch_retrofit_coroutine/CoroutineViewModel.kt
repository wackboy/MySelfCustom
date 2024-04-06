package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfcustom.retorfitutil.ApiResponse
import kotlinx.coroutines.launch

class CoroutineViewModel : ViewModel() {

    private val homePageRepo by lazy {
        HomePageRepo()
    }

    var titleLiveData = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            val ret = homePageRepo.getBannerInfo().value?.data
            if (ret != null) {
                titleLiveData.postValue(ret[0].title)
            }
        }
        homePageRepo.getBannerInfo()
//        homePageRepo.getBannerInfo().observeForever {
//            if (it.isSuccess) {
//                titleLiveData.postValue(it!!.data!![0].title)
//            }
//        }
    }

    fun getHomePage(): LiveData<ApiResponse<List<Banner>>> {
        return homePageRepo.getBannerInfo()
    }



}