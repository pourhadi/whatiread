package com.pourhadi.wir.services

import com.pourhadi.wir.data.dao.ArticleDao
import com.pourhadi.wir.data.entity.Article
import com.pourhadi.wir.modules.DataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.sqlobject.transaction.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Single
import java.net.URL
import java.util.*

@Service
class ArticleService @Autowired constructor(dataSource: DataSource) : BaseService(Jdbi.create(dataSource)) {
    val articleDao: ArticleDao = jdbi.onDemand(ArticleDao::class.java)

    fun add(userId: String,
            title: String,
            url: String): Single<Article> {
        return Single.fromCallable({
                                       val urlId = UUID.randomUUID()

                                      articleDao.insert(urlId.toString(),
                                                           userId,
                                                           title,
                                                           url,
                                                           URL(url).host)

                                       Article(urlId.toString(), userId, url, title)
                                   })
    }

    fun get(userId: String): List<Article> {
        return articleDao.get(userId)
    }
}