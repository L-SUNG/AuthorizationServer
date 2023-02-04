package com.example.AuthorizationServer.service.member;

import com.example.AuthorizationServer.domain.member.Member;
import com.example.AuthorizationServer.domain.member.MemberRepository;
import com.example.AuthorizationServer.web.dto.MemberSaveRequestDto;
import com.example.AuthorizationServer.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto login(String id, String pass) {
        Member entity = memberRepository.findByIdAndPass(id, pass).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
        return new MemberResponseDto(entity);
    }

    /**
     * index 번호로 멤버 검색
     * @param index 등록된 멤버의 index 번호
     * @return 등록 멤버정보
     */
    public MemberResponseDto findByIndex(Long index) {
        Member entity = memberRepository.findById(index).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다. index=" + index));
        return new MemberResponseDto(entity);
    }

    /**
     * 멤버 등록
     * @param memberSaveRequestDto 멤버 등록정보
     * @return 등록된 멤버의 index 번호
     */
    @Transactional
    public Long save(MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        return memberRepository.save(memberSaveRequestDto.toEntity()).getIndex();
    }
}
