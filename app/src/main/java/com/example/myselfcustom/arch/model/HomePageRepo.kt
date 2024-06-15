package com.example.myselfcustom.arch.model

import androidx.lifecycle.LiveData
import com.example.myselfcustom.arch.vm.CoroutineViewModel
import com.example.myselfcustom.arch.log
import com.example.myselfcustom.arch.net.IHomePage
import com.example.myselfcustom.model.meta.Banner
import com.example.myselfcustom.utils.retorfitutil.ApiResponse
import com.example.myselfcustom.utils.retorfitutil.SequenceDataSource
import com.example.myselfcustom.utils.ServiceCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class HomePageDataSource(val scope: CoroutineScope)
    : SequenceDataSource<ApiResponse<List<Banner>>>(scope) {

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

    fun getBannerInfoFlow(): Flow<List<Banner>?> {
        return flow {
            emit(service.getBannerInfo().data)
            log("${CoroutineViewModel.TAG} + getBanner: ")
        }.flowOn(Dispatchers.IO)
            .stateIn(
                scope = scope,
                started = SharingStarted.Eagerly,
                initialValue = null
            )
    }

}