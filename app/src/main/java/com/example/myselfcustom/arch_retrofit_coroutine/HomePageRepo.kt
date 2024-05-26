package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import com.example.myselfcustom.retorfitutil.ApiResponse
import com.example.myselfcustom.retorfitutil.SequenceDataSource
import com.example.myselfcustom.utils.ServiceCreator
import kotlinx.coroutines.CoroutineScope

class HomePageDataSource(scope: CoroutineScope) : SequenceDataSource<ApiResponse<List<Banner>>>(scope) {

    private val service = ServiceCreator.create<IHomePage>()

    fun getBannerInfo(): LiveData<ApiResponse<List<Banner>>> {
        return simpleLiveData {
                executeHttp {
                    service.getBannerInfo()
                }
            }
    }

    fun getBannerInfoSequence() {
        loadSequence {
            simpleLiveData {
                executeHttp {
                    service.getBannerInfo()
                }
            }
        }
    }
}