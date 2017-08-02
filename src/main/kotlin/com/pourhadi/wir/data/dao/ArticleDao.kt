package com.pourhadi.wir.data.dao

import com.pourhadi.wir.data.entity.Article
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import org.jdbi.v3.sqlobject.transaction.Transaction
import javax.management.monitor.StringMonitor

interface ArticleDao {

    @SqlUpdate("insert into articles SET id=:id, userId=:userId, title=:title, url=:url, host=:host")
    fun insert(id: String,
                  userId: String,
                  title: String,
                  url: String,
                  host: String)

    @SqlQuery("select * from articles " +
              "WHERE userId=:userId ORDER BY createdAt DESC")
    fun get(@Bind("userId") userId: String): List<Article>

    @SqlQuery("select * from articles " +
              "ORDER BY createdAt DESC LIMIT :limit")
    fun get(@Bind("limit") limit: Int): List<Article>

    @SqlQuery("select host " +
              "from (" +
              "select host " +
              "from articles " +
              "group by host " +
              "order by COUNT(host) DESC " +
              "LIMIT :limit" +
              ") as T1 " +
              "ORDER BY host")
    fun getTopHosts(limit: Int): List<String>
}