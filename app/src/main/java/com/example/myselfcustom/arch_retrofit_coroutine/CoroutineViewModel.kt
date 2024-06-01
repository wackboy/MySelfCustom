package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfcustom.meta.Articles
import com.example.myselfcustom.meta.Banner
import com.example.myselfcustom.retorfitutil.ApiResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoroutineViewModel : ViewModel() {

    companion object {
        const val TAG = "CoroutineViewModelTAG"
    }

    val homePageDataSource by lazy {
        HomePageDataSource(viewModelScope)
    }

    val articleDataSource by lazy {
        ArticleDataSource(viewModelScope)
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
            homePageDataSource.getBannerInfoFlow()
                .onEach {
                    log(it?.size)
                    log(TAG + "onEach")
                }
                .catch {
                    log(TAG + "catch")
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = null
                )
                .collect()
        }
//        homePageRepo.getBannerInfo()
    }

    fun getHomePage(): LiveData<ApiResponse<List<Banner>>> {
        return homePageDataSource.getBannerInfo()
    }

    fun getSequenceHomeData() {

    }

    fun getArticleContent(): LiveData<ApiResponse<Articles>> {
        return articleDataSource.getArticleByIndex(0)
    }

    fun setLiveData(i: String) {
        _mockLiveData.value = "this is a: $i"
    }



}