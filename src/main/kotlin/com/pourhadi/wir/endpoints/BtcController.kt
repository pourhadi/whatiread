package com.pourhadi.wir.endpoints

import org.springframework.stereotype.Controller
import com.coinbase.api.CoinbaseBuilder
import com.coinbase.api.Coinbase
import com.coinbase.api.entity.Transaction
import com.pourhadi.wir.data.dao.BtcDao
import com.pourhadi.wir.data.dao.UserDao
import com.pourhadi.wir.modules.DataSource
import com.pourhadi.wir.services.BaseService
import org.jdbi.v3.core.Jdbi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpServletRequest
import org.joda.money.Money
import com.coinbase.api.entity.Transfer




@Controller
class BtcController @Autowired constructor(dataSource: DataSource) : BaseService(Jdbi.create(dataSource)){

    val btcDao: BtcDao = jdbi.onDemand(BtcDao::class.java)


    @RequestMapping(value = "/btc/{code}", method = arrayOf(RequestMethod.GET))
    protected fun home(@PathVariable("code") code: String, model: Model, req: HttpServletRequest): String {
        model.addAttribute("code", code)
        return "btc"
    }


    @RequestMapping(value = "/btc/submit", method = arrayOf(RequestMethod.POST))
        fun start(@RequestParam code: String,
                  @RequestParam email: String,
                  model: Model,
                  req: HttpServletRequest): String {
        val cb = CoinbaseBuilder()
            .withApiKey("Rf2LPC9EY2oYrvvx", "mumuzOS5WzQaMwLzpFWygnWsgkkYVi5f")
            .build()


        val value = if (code == "AAAAA") {
            5
        } else {
            btcDao.getValueForCode(code)
        }

        if (value != 0) {
            val t = cb.buy(Money.parse("USD $value.00"))
            t.code // "6H7GYLXZ"
            t.total.toString() // "USD 3"
            t.getPayoutDate().toString() // "2013-02-01T18:00:00-0800"

            val send = Transaction()
            send.to = email
            send.amount = Money.parse("USD $value.00")
            val sent = cb.sendMoney(send)
        }

        model.addAttribute("code", value)
        return "btc"
    }

}