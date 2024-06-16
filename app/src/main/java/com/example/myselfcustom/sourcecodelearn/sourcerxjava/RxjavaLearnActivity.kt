package com.example.myselfcustom.sourcecodelearn.sourcerxjava

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.myselfcustom.BaseActivity
import com.example.myselfcustom.databinding.ActivityRxjavaLearnBinding
import com.example.myselfcustom.ui.slideleftfragment.SlideLeftCommon
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

@Route(path = SlideLeftCommon.RXJAVA_LEARN_ACTIVITY_PATH)
class RxjavaLearnActivity : BaseActivity<ActivityRxjavaLearnBinding>() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/project/tree/") // 设置基础 URL
            .addConverterFactory(GsonConverterFactory.create()) // 可选，添加 Gson 转换器
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val service = retrofit.create<IpServiceGet>()
        service.fetchMsg().subscribeOn(Schedulers.io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe(
            {
                println("haha")
            }, {
                println(it.message)
            }, {
                println("finish")
            }
        )
    }

    override fun createViewBinding(): ActivityRxjavaLearnBinding {
        return ActivityRxjavaLearnBinding.inflate(layoutInflater)
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
