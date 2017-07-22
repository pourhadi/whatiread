package com.pourhadi.wir.util

import org.flywaydb.core.Flyway
import javax.sql.DataSource

object Database {

    const val HOST:String = "DB_HOST"

    const val NAME:String = "DB_NAME"

    const val USERNAME:String = "DB_USERNAME"

    const val PASSWORD:String = "DB_PASSWORD"

    const val SSL:String = "DB_SSL"

    const val PORT:String = "DB_PORT"

    fun jdbcUrl(host: String, port: Int, dbName: String, requireSSL: Boolean, debug: Boolean): String {
        val builder = StringBuilder()
        builder.append("jdbc:mysql://")
        builder.append(host)
        builder.append(":")
        builder.append(port)
        builder.append("/")
        builder.append(dbName)
        builder.append("?verifyServerCertificate=")
        builder.append(if (requireSSL) "true" else "false")
        builder.append("&useSSL=")
        builder.append(if (requireSSL) "true" else "false")
        builder.append("&serverTimezone=UTC")
        if (debug) {
            builder.append("&profileSQL=true")
        }
        return builder.toString()
    }

    fun migrate(dataSource: DataSource, baseline: Boolean, clean: Boolean) {
        val flyway = Flyway()
        flyway.setDataSource(dataSource)
        flyway.setBaselineOnMigrate(baseline)
        if (clean) {
            flyway.clean()
        }
        flyway.migrate()
    }
}

