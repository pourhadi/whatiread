package com.pourhadi.wir.data.dao

import com.pourhadi.wir.data.entity.Article
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction
import javax.management.monitor.StringMonitor

abstract class ArticleDao {

    @Transaction
    fun insertArticle(userId: String,
                      articleId: String,
                      urlId: String,
                      title: String,
                      url: String,
                      host: String) {
        insertUrl(urlId, title, url, host)
        insert(articleId, urlId, userId)
    }

    @SqlUpdate("insert into articles SET id=:id, urlId=:urlId, userId=:userId")
    abstract fun insert(id: String,
                        urlId: String,
                        userId: String)

    @SqlUpdate("insert into urls SET id=:id, title=:title, url=:url, host=:host")
    abstract fun insertUrl(id: String,
                           title: String,
                           url: String,
                           host: String)

    @SqlQuery("select * from articles WHERE userId=:userId ORDER BY createdAt DESC")
    abstract fun get(@Bind("userId") userId: String): List<Article>

    @SqlQuery("select * from articles ORDER BY createdAt DESC LIMIT :limit")
    abstract fun get(@Bind("limit") limit: Int): List<Article>

    @SqlQuery("select host " +
              "from (" +
              "select host " +
              "from urls " +
              "group by host " +
              "order by COUNT(host) DESC " +
              "LIMIT :limit" +
              ") as T1 " +
              "ORDER BY host")
    abstract fun getTopHosts(limit: Int): List<String>
}