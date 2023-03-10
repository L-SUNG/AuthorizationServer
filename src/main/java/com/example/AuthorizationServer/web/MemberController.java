package com.example.authorizationserver.web;

import com.example.authorizationserver.domain.member.Member;
import com.example.authorizationserver.dto.ErrorMsg;
import com.example.authorizationserver.service.member.MemberService;
import com.example.authorizationserver.web.form.member.MemberSignUpForm;
import com.example.authorizationserver.web.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final HttpSession httpSession;
    private final MemberService memberService;


    private Authentication authentication;

    /**
     * 로그인 화면 이동
     * @return 로그인 화면
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {

        // 예외 발생시 알림 표시를 위한 값 설정
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member-login";
    }

    /**
     * 멤버 등록 화면으로 이동
     * @return 멤버 등록 화면
     */
    @GetMapping("/signup")
    public String memberSignUp() {
        return "member-signup";
    }

    /**
     * 멤버 등록 처리
     * @param memberSignUpForm 멤버 등록 입력폼 정보
     * @param bindingResult 유효성 검사 결과
     * @param model 화면 모델
     * @return 신규 멤버 등록완료 화면
     */
    @PostMapping("/signup")
    public String signup(@Valid MemberSignUpForm memberSignUpForm, BindingResult bindingResult, Model model) {

        // 유효성 검사 판정
        if (bindingResult.hasErrors()) {
            // 유효성 검사 에러 발생시 에러 메세지를 추출하여 리스트에 추가
            List<ErrorMsg> errorList = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()) {
                errorList.add(new ErrorMsg(error.getDefaultMessage()));
            }
            if (!errorList.isEmpty()) {
                // 에러 메세지 리스트를 모델에 추가
                model.addAttribute("validationErrors", errorList);
            }
            return "member-signup";
        }

        Member member = new Member();
        try {
            // 멤버등록처리
            member = memberService.create(memberSignUpForm);
        }catch(DataIntegrityViolationException e) {
            // 중복발생
            e.printStackTrace();
            model.addAttribute("validationErrors", new ErrorMsg("이미 등록된 사용자입니다."));
            return "member-signup";
        }catch(Exception e) {
            // 예외발생
            e.printStackTrace();
            model.addAttribute("validationErrors", new ErrorMsg(e.getMessage()));
            return "member-signup";
        }

        // 등록완료 화면에 표시할 멤버의 ID를 지정
        model.addAttribute("id", member.getId());

        return "member-signup-complete";
    }

    /**
     * 마이페이지 화면으로 이동
     * @return 마이페이지 화면
     */
    @GetMapping("/mypage")
    public String memberMyPage(Model model) {
        // 로그인 중인 멤버의 ID 취득
        authentication = MemberUtil.getAuthentication();
        String id = (String)authentication.getPrincipal();
        // 멤버 정보 취득
        Member member = memberService.findById(id);
        // 멤버 정보를 모델에 설정
        model.addAttribute("mypage", member);

        return "member-mypage";
    }
}
