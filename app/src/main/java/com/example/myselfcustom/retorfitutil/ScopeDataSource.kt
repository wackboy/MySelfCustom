package com.example.myselfcustom.retorfitutil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ScopeDataSource(private val scope: CoroutineScope) {

    fun <T> simpleLiveData(block: suspend () -> ApiResponse<T>): LiveData<ApiResponse<T>> {
        val result = MutableLiveData<ApiResponse<T>>()
        scope.launch(Dispatchers.IO) {
            try {
                val response = block()
                result.postValue(response)
            } catch (e: Exception) {
                result.postValue(ApiErrorResponse(e))
            }
        }
        return result
    }

//    fun <T> simpleLiveData2(block: suspend () -> ApiResponse<T>): LiveData<ApiResponse<T>> {
//        return liveData(Dispatchers.IO) {
//            emit(block.invoke())
//        }
//    }



    suspend fun <T> executeHttp(block: suspend () -> ApiResponse<T>) : ApiResponse<T> {
        kotlin.runCatching {
            block.invoke()
        }.onSuccess { data: ApiResponse<T> ->
            return handleHttpOk(data)
        }.onFailure { e ->
            return handleHttpError(e)
        }
        return ApiEmptyResponse()
    }

    private fun <T> handleHttpError(e: Throwable): ApiErrorResponse<T> {
        handlingException(e)
        return ApiErrorResponse(e)
    }

    private fun <T> handleHttpOk(data: ApiResponse<T>): ApiResponse<T> {
        return if (data.isSuccess) {
            getHttpSuccessResponse(data)
        } else {
            handingApiExceptions(data.errorCode, data.errorMsg)
            ApiFailedResponse(data.errorCode, data.errorMsg)
        }
    }

    private fun <T> getHttpSuccessResponse(response: ApiResponse<T>): ApiResponse<T> {
        return if (response.data == null
            || response.data is List<*> && (response.data as List<*>).isEmpty()) {
            ApiEmptyResponse()
        } else {
            ApiSuccessResponse(response.data!!)
        }
    }

    private fun handlingException(e: Throwable?) {

    }

    private fun handingApiExceptions(errorCode: Int?, errorMsg: String?) {

    }

}