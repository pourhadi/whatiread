package com.pourhadi.wir

import com.pourhadi.wir.endpoints.ArticleEndpoint
import com.pourhadi.wir.util.Env
import com.pourhadi.wir.util.ObservableReturnValueHandler
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter



@SpringBootApplication
@ComponentScan(basePackages = arrayOf("com.auth0.example", "com.pourhadi.wir"))
@EnableAutoConfiguration
@PropertySources(PropertySource("classpath:application.properties"), PropertySource("classpath:auth0.properties"))
open class WhatIReadApplication
fun main(args: Array<String>) {
    SpringApplication.run(WhatIReadApplication::class.java, *args)
}


fun isDebug(): Boolean {
    return Env.get("ENV")["ENV"] as Boolean
}