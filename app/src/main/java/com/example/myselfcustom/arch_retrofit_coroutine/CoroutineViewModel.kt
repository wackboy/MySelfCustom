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

    private var titleLiveData = MutableLiveData<String>()
    private var _mockLiveData = MutableLiveData<String>("haha")
    val mockLiveData: LiveData<String> = _mockLiveData

    init {
        viewModelScope.launch {
            val ret = homePageRepo.getBannerInfo().value?.data
            if (ret != null) {
                titleLiveData.postValue(ret[0].title)
            }
        }
//        homePageRepo.getBannerInfo()
    }

    fun getHomePage(): LiveData<ApiResponse<List<Banner>>> {
        return homePageRepo.getBannerInfo()
    }

    fun setLiveData(i: String) {
        _mockLiveData.value = "this is a: $i"
    }



}