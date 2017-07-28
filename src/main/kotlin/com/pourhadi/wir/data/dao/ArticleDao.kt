package com.pourhadi.wir.data.dao

import com.pourhadi.wir.data.entity.Article
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface ArticleDao {

    @SqlUpdate("insert into articles SET userId=:article.userId, url=:article.url, title=:article.title")
    fun insert(article: Article)

    @SqlQuery("select * from articles WHERE userId=:userId ORDER BY createdAt DESC")
    fun get(@Bind("userId") userId:String) : List<Article>

    @SqlQuery("select * from articles ORDER BY createdAt DESC LIMIT :limit")
    fun get(@Bind("limit") limit:Int) : List<Article>
}