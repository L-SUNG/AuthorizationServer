package com.example.AuthorizationServer.web;

import com.example.AuthorizationServer.config.auth.LoginUser;
import com.example.AuthorizationServer.config.auth.dto.SessionUser;
import com.example.AuthorizationServer.domain.member.Member;
import com.example.AuthorizationServer.web.dto.MemberCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/member/signin")
    public String memberSignIn() {
        return "member-signin";
    }

    @PostMapping("/member/signin/complete")
    public String memberSignInComplete(MemberCreateRequestDto dto, Model model) {

        model.addAttribute("id", dto.getId());

        return "member-signin-complete";
    }
}
