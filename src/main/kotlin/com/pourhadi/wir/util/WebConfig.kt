package com.pourhadi.wir.util

import com.google.common.collect.Lists
import com.pourhadi.wir.util.ObservableReturnValueHandler
import org.apache.catalina.Context
import org.apache.catalina.connector.Connector
import org.apache.tomcat.util.descriptor.web.SecurityCollection
import org.apache.tomcat.util.descriptor.web.SecurityConstraint
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.HttpMessageConverters
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration
import org.springframework.boot.autoconfigure.web.WebMvcProperties
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
import javax.annotation.PostConstruct
import javax.annotation.Resource
import org.springframework.format.FormatterRegistry
import org.springframework.context.annotation.ComponentScan
import java.util.*
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite
import org.springframework.core.Ordered
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.*
import java.util.ArrayList


//@Configuration
//@ComponentScan
//open class WebConfig {
//
//    @Bean
//    open fun servletContainer(): EmbeddedServletContainerFactory {
//        val tomcat =  object : TomcatEmbeddedServletContainerFactory() {
//            override fun postProcessContext(context: Context) {
//                val securityConstraint = SecurityConstraint()
//                securityConstraint.userConstraint = "CONFIDENTIAL"
//                val collection = SecurityCollection()
//                collection.addPattern("/*")
//                securityConstraint.addCollection(collection)
//                context.addConstraint(securityConstraint)
//            }
//        }
//
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat
//    }
//
//    private fun initiateHttpConnector(): Connector {
//        val connector = Connector("org.apache.coyote.http11.Http11NioProtocol")
//        connector.scheme = "http"
//        connector.port = 8080
//        connector.secure = false
//        connector.redirectPort = 8443
//
//        return connector
//    }
//}
