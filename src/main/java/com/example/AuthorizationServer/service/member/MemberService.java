package com.example.authorizationserver.service.member;

import com.example.authorizationserver.domain.member.Member;
import com.example.authorizationserver.domain.member.MemberRepository;
import com.example.authorizationserver.web.dto.MemberLoginRequestDto;
import com.example.authorizationserver.web.dto.MemberSaveRequestDto;
import com.example.authorizationserver.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 로그인 처리
     * @param requestDto 로그인 요청 멤버의 정보
     * @return 멤버정보
     */
    public Member login(MemberLoginRequestDto requestDto) {
        Member entity = memberRepository.findByIdAndPass(requestDto.getId(), requestDto.getPass());
        return entity;
    }

    /**
     * seq 번호로 멤버 검색
     * @param seq 등록된 멤버의 seq 번호
     * @return 등록 멤버정보
     */
    public MemberResponseDto findBySeq(Long seq) {
        Member entity = memberRepository.findById(seq).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다. seq=" + seq));
        return new MemberResponseDto(entity);
    }

    /**
     * 멤버 등록
     * @param memberSaveRequestDto 멤버 등록정보
     * @return 등록된 멤버의 seq 번호
     */
    @Transactional
    public Long save(MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        return memberRepository.save(memberSaveRequestDto.toEntity()).getSeq();
    }
}
