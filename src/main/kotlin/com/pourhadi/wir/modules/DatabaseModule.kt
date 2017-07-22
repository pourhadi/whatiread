package com.pourhadi.wir.modules


import com.pourhadi.wir.util.Env
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.pourhadi.wir.util.Database
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Component
open class DataSource: HikariDataSource() {
    init {
        val props = Env.get(Database.HOST, Database.NAME, Database.USERNAME, Database.PASSWORD, Database.SSL, Database.PORT)
        val jdbcUrl = Database.jdbcUrl(props.getProperty(Database.HOST),
                                       props.getProperty(Database.PORT).toInt(),
                                       props.getProperty(Database.NAME),
                                       props.getProperty(Database.SSL).toBoolean(),
                                       false)

        this.jdbcUrl = jdbcUrl
        this.username = props.getProperty(Database.USERNAME)
        this.password = props.getProperty(Database.PASSWORD)
    }
}

