package com.example.AuthorizationServer.web;

import com.example.AuthorizationServer.config.auth.LoginUser;
import com.example.AuthorizationServer.config.auth.dto.SessionUser;
import com.example.AuthorizationServer.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser member) {
        return "index";
    }

    /**
     * 로그인 화면 이동
     * @return 로그인 화면
     */
    @GetMapping("/member/login")
    public String memberLogin() {
        return "member-login";
    }

    /**
     * 멤버 등록 화면으로 이동
     * @return 멤버 등록 화면
     */
    @GetMapping("/member/signin")
    public String memberSignIn() {
        return "member-signin";
    }

    /**
     * 신규 멤버 등록완료 화면으로 이동
     * @param dto 신규 멤버 등록정보
     * @param model 모델
     * @return 신규 멤버 등록완료 화면
     */
    @PostMapping("/member/signin/complete")
    public String memberSignInComplete(MemberSaveRequestDto dto, Model model) {

        // 등록완료 화면에 표시할 멤버의 ID를 지정
        model.addAttribute("id", dto.getId());

        return "member-signin-complete";
    }
}
