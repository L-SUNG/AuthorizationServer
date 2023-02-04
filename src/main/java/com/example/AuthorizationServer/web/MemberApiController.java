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
}
