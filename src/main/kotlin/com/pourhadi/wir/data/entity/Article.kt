package com.pourhadi.wir.data.entity

import java.net.URL
import java.sql.Timestamp

data class Article(val id: Int?,
                   val userId: String,
                   val url: String,
                   val title: String,
                   val createdAt: Timestamp?) {
    constructor(userId: String, title:String, url: String): this(null, userId, url, title, null)

    val source : String = URL(url).host
}