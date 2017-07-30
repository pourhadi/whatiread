package com.auth0.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.pourhadi.wir.WhatIReadApplicationKt.isDebug;

@SuppressWarnings("unused")
@Controller
public class LoginController {

    private final AuthController controller;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LoginController(AuthController controller) {this.controller = controller;}

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(final HttpServletRequest req) {
        logger.debug("Performing login");

        String redirectUri;
        if (isDebug()) {
            redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/callback";
        } else {
            redirectUri = req.getScheme() + "://" + req.getServerName() + "/callback";
        }

        String authorizeUrl = controller.buildAuthorizeUrl(req, redirectUri);
        return "redirect:" + authorizeUrl;
    }

}
