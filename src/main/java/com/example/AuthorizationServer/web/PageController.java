package com.example.authorizationserver.web;

import com.example.authorizationserver.config.auth.LoginMember;
import com.example.authorizationserver.config.auth.dto.SessionMember;
import com.example.authorizationserver.domain.member.Member;
import com.example.authorizationserver.service.member.MemberService;
import com.example.authorizationserver.web.dto.MemberLoginRequestDto;
import com.example.authorizationserver.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final HttpSession httpSession;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(@LoginMember SessionMember member) {
        return "index";
    }

    /**
     * 로그인 화면 이동
     * @return 로그인 화면
     */
    @GetMapping("/member/login")
    public String memberLogin(Model model) {
        return "member-login";
    }

    /**
     * 로그아웃 처리
     * @return index 화면
     */
    @GetMapping("/member/logout")
    public String memberLogout() {
        // 세션 초기화
        httpSession.invalidate();
        return "redirect:/";
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

    /**
     * 멤버 로그인
     * @param requestDto 멤버 등록정보
     * @return 등록된 멤버 Seq
     */
    @PostMapping("/member/loginProc")
    public String login(MemberLoginRequestDto requestDto, RedirectAttributes redirectAttributes) {
        // 로그인 처리
        Member member = memberService.login(requestDto);

        if (member != null) {
            // 로그인 멤버의 세션 생성
            httpSession.setAttribute("member", new SessionMember(member));
            // index 페이지로 리다이렉트
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "로그인에 실패하였습니다. 아이디와 패스워드를 확인해주세요.");
            return "redirect:/member/login";
        }
    }

    /**
     * 마이페이지 화면으로 이동
     * @return 마이페이지 화면
     */
    @GetMapping("/member/mypage")
    public String memberMyPage(Model model) {

        // 로그인 상태 확인
        if (!loginCheck()) {
            return "redirect:/member/login";
        }

        return "member-mypage";
    }

    /**
     * 로그인 상태 확인
     * TODO:SpringSecurity를 활용한 로그인 검사로 전환 필요
     * @return 확인결과
     */
    private boolean loginCheck() {
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("member");
        // 로그인 상태 확인
        if (sessionMember == null) {
            // 비로그인 상태
            return false;
        } else {
            // 로그인 상태
            return true;
        }
    }
}
