package com.pourhadi.wir.services

import com.pourhadi.wir.data.dao.ArticleDao
import com.pourhadi.wir.data.entity.Article
import com.pourhadi.wir.modules.DataSource
import org.jdbi.v3.core.Jdbi
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
                                       val articleId = UUID.randomUUID()
                                       val urlId = UUID.randomUUID()

                                       articleDao.insertArticle(userId.toString(),
                                                                articleId.toString(),
                                                                urlId.toString(),
                                                                title,
                                                                url,
                                                                URL(url).host)

                                       Article(articleId.toString(), userId.toString(), url, title)
                                   })
    }

    fun get(userId: String): List<Article> {
        return articleDao.get(userId)
    }
}