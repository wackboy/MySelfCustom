package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import com.example.myselfcustom.retorfitutil.ApiResponse
import com.example.myselfcustom.retorfitutil.BaseRepository
import com.example.myselfcustom.utils.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class HomePageRepo : BaseRepository() {

    private val service = ServiceCreator.create<IHomePage>()

    fun getBannerInfo(): LiveData<ApiResponse<List<Banner>>> {
        return simpleLiveData {
            executeHttp {
                service.getBannerInfo()
            }
        }
    }

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }

}