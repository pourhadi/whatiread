package com.pourhadi.wir.util

import org.springframework.web.context.request.async.WebAsyncUtils
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.core.MethodParameter
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import rx.Observable


class ObservableReturnValueHandler : HandlerMethodReturnValueHandler {

    override fun supportsReturnType(returnType: MethodParameter): Boolean {
        val parameterType = returnType.parameterType
        return Observable::class.java.isAssignableFrom(parameterType)
    }

    @Throws(Exception::class)
    override fun handleReturnValue(returnValue: Any?,
                                   returnType: MethodParameter, mavContainer: ModelAndViewContainer,
                                   webRequest: NativeWebRequest) {
        if (returnValue == null) {
            mavContainer.isRequestHandled = true
            return
        }

        val deferredResult = DeferredResult<Any>()
        val observable = returnValue as Observable<*>
        observable!!.subscribe({ result -> deferredResult.setResult(result) }, { errors -> deferredResult.setErrorResult(errors) })

        WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(deferredResult, mavContainer)

    }


}