package com.auth0.example;

import com.auth0.SessionUtils;
import com.pourhadi.wir.util.SessionVars;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class Auth0Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String accessToken = (String) SessionUtils.get(req, "accessToken");
        String idToken = (String) SessionUtils.get(req, "idToken");
        if (accessToken == null && idToken == null) {

            if (Objects.equals(req.getRequestURI(), "/post")) {
                String redirectUri = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/login";

                SessionUtils.set(req, SessionVars.INSTANCE.getORIGINAL_URI(), req.getRequestURL() + "?" + req.getQueryString());

                res.getWriter().write("window.location = '" + redirectUri + "';");
                return;
            }

            res.sendRedirect("/login");
            return;
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
