package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfcustom.retorfitutil.ApiResponse
import kotlinx.coroutines.launch

class CoroutineViewModel : ViewModel() {

    val homePageDataSource by lazy {
        HomePageDataSource(viewModelScope)
    }

    private var titleLiveData = MutableLiveData<String>()
    private var _mockLiveData = MutableLiveData<String>("haha")
    val mockLiveData: LiveData<String> = _mockLiveData

    init {
        viewModelScope.launch {
            val ret = homePageDataSource.getBannerInfo().value?.data
            if (ret != null) {
                titleLiveData.postValue(ret[0].title)
            }
        }
//        homePageRepo.getBannerInfo()
    }

    fun getHomePage(): LiveData<ApiResponse<List<Banner>>> {
        return homePageDataSource.getBannerInfo()
    }

    fun getSequenceHomeData() {

    }

    fun setLiveData(i: String) {
        _mockLiveData.value = "this is a: $i"
    }



}