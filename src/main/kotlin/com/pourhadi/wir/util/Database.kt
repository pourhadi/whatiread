package com.pourhadi.wir.util

import org.flywaydb.core.Flyway
import javax.sql.DataSource

object Database {

    const val HOST:String = "HOST"

    const val NAME:String = "NAME"

    const val USERNAME:String = "USERNAME"

    const val PASSWORD:String = "PASSWORD"

    const val SSL:String = "SSL"

    const val PORT:String = "PORT"

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

