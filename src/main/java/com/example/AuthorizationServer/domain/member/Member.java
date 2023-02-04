package com.example.AuthorizationServer.domain.member;

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
    private Long index;

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

    @Builder
    public Member(Long index, String id, String pass, String name, String email, String picture) {
        this.index = index;
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }


}
