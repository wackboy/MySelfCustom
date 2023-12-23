package com.example.myselfcustom

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


class RxjavaTest {

    @Test
    fun testRxjava() {
        val observable = Observable.create {
            it.onNext("item1")
            it.onNext("item2")
            it.onComplete()
        }
        val observable2 = Observable.just("item1", "item2")
        observable2.subscribe {
            println(it)
        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun test2() {
        Observable.range(0, 5)
            .subscribe {
                println(it)
            }
        Observable.just("cn")
            .map {
                "https://zhihu.$it"
            }
            .subscribe {
                println(it)
            }
        Observable.fromIterable(listOf("asd", "fda", "afaf"))
            .flatMap {
               Observable.just( it + "host")
            }.cast(String::class.java)
            .subscribe {
                println(it)
            }

        Observable.just("1", "2", "3")
            .buffer(2)
            .subscribe {
                println(it)
            }
    }

    @SuppressLint("CheckResult")
    @Test
    fun test3() {
        Observable.just("1", "2", "3")
            .mergeWith(Observable.just("4", "5" ,"6"))
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .subscribe {
//                println(Thread.currentThread().name + ": $it")
            }

        Observable.zip(
            Observable.just("1", "2", "3", "4"),
            Observable.just("a", "b", "c")
        ) { t1, t2 ->
            t1 + t2
        }.subscribe {
            println(it)
        }

        // 注意和zip的区别
        Observable.combineLatest(
            Observable.just("1", "2", "3", "4"),
            Observable.just("a", "b", "c")
        ) { t1, t2 ->
            t1 + t2
        }.subscribe {
            println(it)
        }
    }

    @Test
    fun test4() {
        val dispose = Observable.just("1", "2", "3")
            .doOnNext {
                println(it)
            }
            .subscribe({
                // onNext
                println("onNext")
            }, {
                // onError
               println("onError")
            }, {
                // onComplete
                println("onComplete")
            })
        dispose.dispose()
    }

    @SuppressLint("CheckResult")
    @Test
    fun test5() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/project/tree/") // 设置基础 URL
            .addConverterFactory(GsonConverterFactory.create()) // 可选，添加 Gson 转换器
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val service = retrofit.create<IpServiceGet>()
        service.fetchMsg().subscribeOn(Schedulers.io()).
        subscribe {
           println(it.data)
            println("haha")
        }
    }
}

data class Data(
    @SerializedName("articleList")
    var articleList: List<String>? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("children")
    var children: List<String>? = null,
    @SerializedName("courseId")
    var courseId: Int = 0,
    @SerializedName("cover")
    var cover: String? = null,
    @SerializedName("desc")
    var desc: String? = null,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("lisense")
    var lisense: String? = null,
    @SerializedName("lisenseLink")
    var lisenseLink: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("order")
    var order: Long = 0,
    @SerializedName("parentChapterId")
    var parentChapterId: Int = 0,
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("userControlSetTop")
    var userControlSetTop: Boolean = false,
    @SerializedName("visible")
    var visible: Int = 0
)

data class JsonRootBean(
    @SerializedName("data")
    var data: List<Data>? = null,
    @SerializedName("errorCode")
    var errorCode: Int = 0,
    @SerializedName("errorMsg")
    var errorMsg: String? = null
)

interface IpServiceGet {
    @GET("json/")
    fun fetchMsg(): Observable<JsonRootBean>
}

























