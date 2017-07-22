package com.pourhadi.wir.util

import java.util.*

object Env {

    const val PORT: String = "PORT"

    fun get(vararg values: String): Properties {
        val properties = Properties()
        for (value: String in values) {
            properties.setProperty(value, System.getenv(value))
        }
        return properties
    }
}