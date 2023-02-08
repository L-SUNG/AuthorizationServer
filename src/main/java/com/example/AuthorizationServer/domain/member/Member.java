package com.example.authorizationserver.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 50, unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String pass;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = true)
    private String email;

    @Column(nullable = true)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long seq, String id, String pass, String name, String email, String picture, Role role) {
        this.seq = seq;
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
