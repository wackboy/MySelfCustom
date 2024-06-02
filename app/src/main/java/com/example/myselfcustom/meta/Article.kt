package com.example.myselfcustom.meta

class Article {
}

/**
 * Copyright 2024 bejson.com
 */


/**
 * Auto-generated: 2024-05-27 23:58:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
data class Datas (
    var adminAdd: Boolean = false,
    var apkLink: String? = null,
    var audit: Int = 0,
    var author: String? = null,
    var canEdit: Boolean = false,
    var chapterId: Int = 0,
    var chapterName: String? = null,
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String? = null,
    var descMd: String? = null,
    var envelopePic: String? = null,
    var fresh: Boolean = false,
    var host: String? = null,
    var id: Int = 0,
    var link: String? = null,
    var niceDate: String? = null,
    var niceShareDate: String? = null,
    var origin: String? = null,
    var prefix: String? = null,
    var projectLink: String? = null,
    var publishTime: Long = 0,
    var realSuperChapterId: Int = 0,
    var selfVisible: Int = 0,
    var shareDate: Long = 0,
    var shareUser: String? = null,
    var superChapterId: Int = 0,
    var superChapterName: String? = null,
    var tags: List<Tag>? = null,
    var title: String? = null,
    var type: Int = 0,
    var userId: Long = 0,
    var visible: Int = 0,
    var zan: Int = 0,
)

/**
 * Copyright 2024 bejson.com
 */


data class Articles(
    var curPage: Int = 0,
    var datas: List<Datas>? = null,
    var offset: Int = 0,
    var over: Boolean = false,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0,
)

data class Tag(
    val name: String,
    val url: String
)