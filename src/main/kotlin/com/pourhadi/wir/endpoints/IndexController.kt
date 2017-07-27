package com.pourhadi.wir.endpoints

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest

@Controller
open class IndexController {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    protected fun home(model: Map<String, Any>, req: HttpServletRequest): String {
        return "index"
    }

}