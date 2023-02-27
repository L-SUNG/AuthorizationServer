package com.example.authorizationserver.service.member;

import com.example.authorizationserver.domain.member.Member;
import com.example.authorizationserver.domain.member.MemberRepository;
import com.example.authorizationserver.domain.member.Role;
import com.example.authorizationserver.web.dto.MemberLoginRequestDto;
import com.example.authorizationserver.web.dto.MemberSaveRequestDto;
import com.example.authorizationserver.web.dto.MemberResponseDto;
import com.example.authorizationserver.web.form.MemberSignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

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

    /**
     * 멤버 등록
     * @param memberSignUpForm 입력받은 멤버 정보
     * @return 등록된 멤버 정보
     */
    public Member create(MemberSignUpForm memberSignUpForm) {

        Member member = new Member();
        // 회원등록 Form에서 받아온 값 설정
        member.setId(memberSignUpForm.getId());
        member.setName(memberSignUpForm.getName());
        // 기본 역할 설정
        member.setRole(Role.MEMBER);
        // 패스워드 암호화
        member.setPass(passwordEncoder.encode(memberSignUpForm.getPass()));
        this.memberRepository.save(member);

        return member;
    }
}
