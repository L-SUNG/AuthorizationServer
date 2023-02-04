package com.example.authorizationserver.web.dto;

import com.example.authorizationserver.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLoginRequestDto {

    private String id;
    private String pass;

    @Builder
    public MemberLoginRequestDto(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .pass(pass)
                .build();
    }
}
