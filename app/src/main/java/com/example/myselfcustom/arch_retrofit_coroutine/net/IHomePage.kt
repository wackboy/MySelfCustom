package com.example.myselfcustom.arch_retrofit_coroutine.net

import com.example.myselfcustom.meta.Articles
import com.example.myselfcustom.meta.Banner
import com.example.myselfcustom.retorfitutil.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IHomePage {

    @GET("banner/json")
    suspend fun getBannerInfo() : ApiResponse<List<Banner>>

    @GET("article/list/{index}/json")
    suspend fun getArticle(@Path("index") index: Int): ApiResponse<Articles>

    @GET("article/list/{index}/json")
    suspend fun fetchArticleByIndex(@Path("index") index: Int): ApiResponse<Articles>

}