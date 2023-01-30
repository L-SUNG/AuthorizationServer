package com.example.AuthorizationServer.service.member;

import com.example.AuthorizationServer.domain.member.Member;
import com.example.AuthorizationServer.domain.member.MemberRepository;
import com.example.AuthorizationServer.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto login(String id, String pass) {
        Member entity = memberRepository.findByIdAndPass(id, pass).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
        return new MemberResponseDto(entity);
    }

    public MemberResponseDto findByIndex(Long index) {
        Member entity = memberRepository.findById(index).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다. index=" + index));
        return new MemberResponseDto(entity);
    }
}
