package com.example.myselfcustom.arch_retrofit_coroutine

import androidx.lifecycle.LiveData
import com.example.myselfcustom.meta.Articles
import com.example.myselfcustom.retorfitutil.ApiResponse
import com.example.myselfcustom.retorfitutil.ScopeDataSource
import com.example.myselfcustom.utils.ServiceCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArticleDataSource(scope: CoroutineScope) : ScopeDataSource(scope) {

    private val service = ServiceCreator.create<IHomePage>()

    fun getArticleByIndex(index: Int): LiveData<ApiResponse<Articles>> {
        return simpleLiveData {
            executeHttp {
                service.getArticle(index)
            }
        }
    }

    fun getArticlesFlow(index: Int): Flow<ApiResponse<Articles>> {
        return flow {
            emit(service.getArticleFlow(index))

        }.flowOn(Dispatchers.IO)

    }

}