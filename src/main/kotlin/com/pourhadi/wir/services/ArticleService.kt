package com.pourhadi.wir.services

import com.pourhadi.wir.data.dao.ArticleDao
import com.pourhadi.wir.data.entity.Article
import com.pourhadi.wir.modules.DataSource
import org.jdbi.v3.core.Jdbi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Single

@Service
class ArticleService @Autowired constructor(dataSource: DataSource) : BaseService(Jdbi.create(dataSource)) {
    val articleDao: ArticleDao = jdbi.onDemand(ArticleDao::class.java)

    fun create(article: Article): Single<Article> {
        return Single.fromCallable({
            articleDao.insert(article)
            article
        })
    }

    fun get(userId: String) : List<Article> {
        return articleDao.get(userId)
    }
}