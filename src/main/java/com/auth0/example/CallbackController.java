package com.auth0.example;

import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import com.pourhadi.wir.data.entity.User;
import com.pourhadi.wir.data.entity.UserCode;
import com.pourhadi.wir.services.UserService;
import com.pourhadi.wir.util.SessionVars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rx.Single;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
@Controller
public class CallbackController {

    @Autowired
    private AuthController controller;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private UserService userService;

    private final String redirectOnFail;

    private final String redirectOnSuccess;

    public CallbackController() {
        this.redirectOnFail = "/login";
        this.redirectOnSuccess = "/portal/home";
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    protected void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void postCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            Tokens tokens = controller.handle(req);
            SessionUtils.set(req, "accessToken", tokens.getAccessToken());
            SessionUtils.set(req, "idToken", tokens.getIdToken());

            AuthAPI api = new AuthAPI(appConfig.getDomain(), appConfig.getClientId(), appConfig.getClientSecret());
            Request request = api.userInfo(tokens.getAccessToken());
            try {
                UserInfo userInfo = (UserInfo) request.execute();
                String id = (String) userInfo.getValues().get("user_id");
                String email = (String) userInfo.getValues().get("email");
                String nickname = (String) userInfo.getValues().get("nickname");

                userService.updateAndGetOrCreate(id, nickname, email)
                           .subscribe(user -> {
                               try {
                                   SessionUtils.set(req, "user", user);

                                   String original = (String)SessionUtils.get(req, SessionVars.INSTANCE.getORIGINAL_URI());
                                   if (original != null) {
                                       SessionUtils.remove(req, SessionVars.INSTANCE.getORIGINAL_URI());
                                       res.sendRedirect(original);
                                       return;
                                   }

                                   res.sendRedirect(redirectOnSuccess);
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }, error -> {
                               try {
                                   res.sendRedirect(redirectOnFail);
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           });

            } catch (Auth0Exception e) {
                res.sendRedirect(redirectOnFail);
            }

        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }

}
