package com.pourhadi.wir.endpoints

import com.pourhadi.wir.data.entity.Article
import com.pourhadi.wir.services.ArticleService
import com.pourhadi.wir.services.UserService
import com.pourhadi.wir.util.Endpoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.DeferredResult
import rx.Scheduler
import rx.schedulers.Schedulers

@Controller
open class ArticleEndpoint : Endpoint() {

    @Autowired
    private var userService: UserService? = null

    @Autowired
    private var articleService: ArticleService? = null

    @RequestMapping(value = "/post", method = arrayOf(RequestMethod.GET))
    fun post(@RequestParam code: String,
             @RequestParam u: String,
             @RequestParam n: String,
             @RequestHeader headers: Map<String, String>): DeferredResult<String> {
        val result = DeferredResult<String>()

        var titleTrimmed = n
        if (titleTrimmed.length > 255) {
            titleTrimmed = n.substring(IntRange(0, 254))
        }
        userService!!.getFromCode(code)
            .flatMap { response -> articleService!!.create(Article(response.userId, titleTrimmed, u)) }
            .observeOn(Schedulers.immediate())
            .subscribe({ _ ->
                           result.setResult("redirect:" + u)
                       })
        return result
    }
}