package com.example.authorizationserver.web.dto.member;

import com.example.authorizationserver.domain.member.Member;
import com.example.authorizationserver.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    private final String id;
    private final String pass;
    private final String name;

    @Builder
    public MemberSaveRequestDto(String id, String pass, String email, String name) {
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .pass(pass)
                .name(name)
                .role(Role.MEMBER)
                .build();
    }
}
