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
        val jdbcUrl: String

        val envProp = Env.get("ENV")
        if (envProp.getProperty("ENV") == "PROD") {
            val props = Env.get("CLEARDB_DATABASE_URL")
            jdbcUrl = props.getProperty("CLEARDB_DATABASE_URL")
        } else {
            val props = Env.get(Database.HOST, Database.NAME, Database.USERNAME, Database.PASSWORD, Database.SSL, Database.PORT)
            jdbcUrl = Database.jdbcUrl(props.getProperty(Database.HOST),
                                       props.getProperty(Database.PORT).toInt(),
                                       props.getProperty(Database.NAME),
                                       props.getProperty(Database.SSL).toBoolean(),
                                       false)
        }
        this.jdbcUrl = jdbcUrl

    }
}