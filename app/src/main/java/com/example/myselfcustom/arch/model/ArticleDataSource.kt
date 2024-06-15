package com.example.myselfcustom.arch.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myselfcustom.arch.net.IHomePage
import com.example.myselfcustom.model.meta.Articles
import com.example.myselfcustom.utils.retorfitutil.ApiResponse
import com.example.myselfcustom.utils.retorfitutil.ScopeDataSource
import com.example.myselfcustom.utils.ServiceCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleDataSource(scope: CoroutineScope) : ScopeDataSource(scope) {

    private val tag = "ArticleDataSourceTAG"

    private val service = ServiceCreator.create<IHomePage>()

    fun getArticleByIndex(index: Int): LiveData<ApiResponse<Articles>> {
        return simpleLiveData {
            executeHttp {
                service.getArticle(index)
            }
        }
    }

    suspend fun fetchArticleByIndex(index: Int): ApiResponse<Articles> {
        return withContext(Dispatchers.IO) {
            Log.d(tag, "fetchArticleByIndex 当前的线程是：" + Thread.currentThread().name)
            executeHttp {
                service.fetchArticleByIndex(index)
            }
        }
    }

}