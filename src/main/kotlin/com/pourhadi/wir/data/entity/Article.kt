package com.pourhadi.wir.data.entity

import java.net.URL
import java.sql.Timestamp

data class Article(val id: String,
                   val userId: String,
                   val url: String,
                   val title: String,
                   val createdAt: Timestamp?) {
    constructor(id: String, userId: String, title:String, url: String): this(id, userId, url, title, null)

    val source : String = URL(url).host
}