package com.example.myselfcustom.arch_retrofit_coroutine.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myselfcustom.arch_retrofit_coroutine.model.ArticleDataSource
import com.example.myselfcustom.arch_retrofit_coroutine.model.HomePageDataSource
import com.example.myselfcustom.meta.Articles
import com.example.myselfcustom.meta.Banner
import com.example.myselfcustom.retorfitutil.ApiResponse
import com.example.myselfcustom.utils.CacheOnSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private var _pageNum = MutableStateFlow<Int>(0)
    val pageNum: StateFlow<Int> = _pageNum

    // todo 实现在repo中
    private val fetchArtiContent = CacheOnSuccess({

    }) {
        _pageNum.value = 1
        articleDataSource.fetchArticleByIndex(0)
    }

    init {

        viewModelScope.launch {
            fetchArtiContent.getOrAwait()
//            val ret = homePageDataSource.getBannerInfo().value?.data
//            if (ret != null) {
//                titleLiveData.postValue(ret[0].title)
//            }
//            homePageDataSource.getBannerInfoFlow()
//                .onEach {
//                    log(it?.size)
//                    log(TAG + "onEach")
//                }
//                .catch {
//                    log(TAG + "catch")
//                }
//                .stateIn(
//                    scope = viewModelScope,
//                    started = SharingStarted.WhileSubscribed(5000),
//                    initialValue = null
//                )
//                .collect()
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




    suspend fun fetchArticleContent(): ApiResponse<Articles> {
        _pageNum.value = 1
        return fetchArtiContent.getOrAwait() as ApiResponse<Articles>
    }

    fun setLiveData(i: String) {
        _mockLiveData.value = "this is a: $i"
    }



}