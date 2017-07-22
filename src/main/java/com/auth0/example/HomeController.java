package com.auth0.example;

import com.auth0.SessionUtils;
import com.pourhadi.wir.data.entity.Article;
import com.pourhadi.wir.data.entity.User;
import com.pourhadi.wir.services.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
public class HomeController {

    private final ArticleService articleService;

    @Autowired
    public HomeController(ArticleService articleService) {
        this.articleService = articleService;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/portal/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final HttpServletRequest req) {
        logger.info("Home page");
        String accessToken = (String) SessionUtils.get(req, "accessToken");
        String idToken = (String) SessionUtils.get(req, "idToken");
        User user = (User)SessionUtils.get(req, "user");
        if (user != null) {
            model.put("nickname", user.getNickname());
            model.put("code", user.getCode());
        }

        List<Article> articles = articleService.get(user.getId());
        model.put("articles", articles);
        return "home";
    }

}
