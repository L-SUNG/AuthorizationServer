package com.example.authorizationserver.web.dto;

import com.example.authorizationserver.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String id;
    private String name;
    private String email;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
    }
}
