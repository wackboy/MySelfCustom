package com.example.myselfcustom.arch_retrofit_coroutine

import com.example.myselfcustom.retorfitutil.ApiResponse
import retrofit2.http.GET

interface IHomePage {

    @GET("banner/json")
    suspend fun getBannerInfo() : ApiResponse<List<Banner>>

}