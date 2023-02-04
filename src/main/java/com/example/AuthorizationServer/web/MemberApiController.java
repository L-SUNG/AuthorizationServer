package com.example.AuthorizationServer.web;

import com.example.AuthorizationServer.service.member.MemberService;
import com.example.AuthorizationServer.web.dto.MemberSaveRequestDto;
import com.example.AuthorizationServer.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

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
     * index 번호에 의한 멤버 검색
     * @param index 멤버 index 번호
     * @return 멤버 정보
     */
    @GetMapping("/api/v1/member/{index}")
    public MemberResponseDto findByIndex(@PathVariable Long index) {
        return memberService.findByIndex(index);
    }

    /**
     * 신규 멤버 등록
     * @param requestDto 멤버 등록정보
     * @return 등록된 멤버 Index
     */
    @PostMapping("/api/v1/member")
    public Long save(@RequestBody MemberSaveRequestDto requestDto) {
        return memberService.save(requestDto);
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
