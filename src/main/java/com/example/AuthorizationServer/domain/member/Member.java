package com.example.AuthorizationServer.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Id
    @Column(length = 50, nullable = false)
    private String id;

    @Column(length = 20, nullable = false)
    private String pass;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = true)
    private String picture;

    @Builder
    public Member(Long index, String id, String pass, String name, String email, String picture) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }


}
