package com.example.myselfcustom.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myselfcustom.utils.retorfitutil.ApiEmptyResponse
import com.example.myselfcustom.utils.retorfitutil.ApiErrorResponse
import com.example.myselfcustom.utils.retorfitutil.ApiFailedResponse
import com.example.myselfcustom.utils.retorfitutil.ApiResponse
import com.example.myselfcustom.utils.retorfitutil.ApiSuccessResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://www.wanandroid.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

}

class ResultBuilder<T> {
    var onSuccess: (data: T?) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, _ -> }
    var onError: (e: Throwable) -> Unit = { e -> }
    var onComplete: () -> Unit = {}
}

@MainThread
inline fun <T> LiveData<ApiResponse<T>>.observeState(
    owner: LifecycleOwner,
    listenerBuilder: ResultBuilder<T>.() -> Unit
) {

    val listener = ResultBuilder<T>().also(listenerBuilder)
    observe(owner) { apiResponse ->
        when (apiResponse) {
            is ApiSuccessResponse -> listener.onSuccess(apiResponse.response)
            is ApiEmptyResponse -> listener.onDataEmpty()
            is ApiFailedResponse -> listener.onFailed(apiResponse.errorCode, apiResponse.errorMsg)
            is ApiErrorResponse -> listener.onError(apiResponse.throwable)
        }
        listener.onComplete()
    }

}

inline fun <T> LiveData<T>.observeOnce(
    owner: LifecycleOwner,
    observer: Observer<T>
)  {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}
