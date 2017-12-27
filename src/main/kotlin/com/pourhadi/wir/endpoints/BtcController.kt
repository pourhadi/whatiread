package com.pourhadi.wir.endpoints

import org.springframework.stereotype.Controller
import com.coinbase.api.CoinbaseBuilder
import com.coinbase.api.Coinbase
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest


@Controller
class BtcController {

    @RequestMapping(value = "/btc/{code}", method = arrayOf(RequestMethod.GET))
    protected fun home(@PathVariable("code") code: String, model: Model, req: HttpServletRequest): String {
        model.addAttribute("code", code)
        return "btc"
    }


        fun start(@PathVariable("code") code: String) {
        val cb = CoinbaseBuilder()
            .withApiKey(System.getenv("Rf2LPC9EY2oYrvvx"), System.getenv("mumuzOS5WzQaMwLzpFWygnWsgkkYVi5f"))
            .build()


    }

}