package com.example.authorizationserver.config.auth.dto;

import com.example.authorizationserver.domain.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private Long seq;
    private String id;
    private String name;
    private String email;
    private String picture;

    public SessionMember(Member member) {
        if (member != null) {
            this.seq = member.getSeq();
            this.id = member.getId();
            this.name = member.getName();
            this.email = member.getEmail();
            this.picture = member.getPicture();
        }
    }
}
