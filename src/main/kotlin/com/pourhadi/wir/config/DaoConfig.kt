package com.pourhadi.wir.config

import com.pourhadi.wir.data.dao.ArticleDao
import com.pourhadi.wir.modules.DataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
    open class DaoConfig @Autowired constructor(dataSource: DataSource) {

    val jdbi: Jdbi = Jdbi.create(dataSource)

    init {
        jdbi.installPlugin(SqlObjectPlugin())
        jdbi.installPlugin(KotlinPlugin())
        jdbi.installPlugin(KotlinSqlObjectPlugin())
    }

    @Bean
    open fun articleDao(): ArticleDao = jdbi.onDemand(ArticleDao::class.java)
}