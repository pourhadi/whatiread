package com.pourhadi.wir.endpoints

import com.pourhadi.wir.data.dao.ArticleDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest

@Controller
open class IndexController @Autowired constructor(val articleDao: ArticleDao){

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    protected fun home(model: Model, req: HttpServletRequest): String {

        val mostRecent = articleDao.get(10)
        model.addAttribute("mostRecent", mostRecent)

        val topHosts = articleDao.getTopHosts(10)

        val topHostsList = ArrayList<TopHost>()
        topHosts.mapTo(topHostsList) { TopHost(it) }
        model.addAttribute("topHosts", topHostsList)
        return "index"
    }

}

data class TopHost(val name: String)