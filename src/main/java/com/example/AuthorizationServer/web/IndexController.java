package com.example.AuthorizationServer.web;

import com.example.AuthorizationServer.config.auth.LoginUser;
import com.example.AuthorizationServer.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser member) {
        return "index";
    }

    @GetMapping("/member/login")
    public String memberLogin() {
        return "member-login";
    }
}
