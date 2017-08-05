package com.pourhadi.wir.endpoints

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
    open class TestController {

    @RequestMapping("/test", method = arrayOf(RequestMethod.GET))
    fun test(model: Model): String {
        model.addAttribute("content", "test")
        return "test"
    }
}