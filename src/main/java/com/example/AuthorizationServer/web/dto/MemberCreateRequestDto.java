package com.example.AuthorizationServer.web.dto;

import com.example.AuthorizationServer.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberCreateRequestDto {

    private String id;
    private String pass;
    private String name;
    private String email;

    @Builder
    public MemberCreateRequestDto(String id, String password, String email, String name) {
        this.id = id;
        this.pass = password;
        this.name = name;
        this.email = email;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .pass(pass)
                .name(name)
                .email(email)
                .build();
    }
}
