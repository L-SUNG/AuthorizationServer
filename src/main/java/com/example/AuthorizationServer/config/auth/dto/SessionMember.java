package com.example.authorizationserver.config.auth.dto;

import com.example.authorizationserver.domain.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private Long index;
    private String id;
    private String name;
    private String email;
    private String picture;

    public SessionMember(Member member) {
        if (member != null) {
            this.index = member.getIndex();
            this.id = member.getId();
            this.name = member.getName();
            this.email = member.getEmail();
            this.picture = member.getPicture();
        }
    }
}
