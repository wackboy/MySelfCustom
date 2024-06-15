package com.example.myselfcustom.arch.net

import com.example.myselfcustom.model.meta.Articles
import com.example.myselfcustom.model.meta.Banner
import com.example.myselfcustom.utils.retorfitutil.ApiResponse
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