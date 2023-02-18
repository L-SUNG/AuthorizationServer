package com.example.authorizationserver.web;

import com.example.authorizationserver.config.auth.LoginMember;
import com.example.authorizationserver.config.auth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PageController {

    @GetMapping("/")
    public String index(@LoginMember SessionMember member) {
        return "index";
    }
}
