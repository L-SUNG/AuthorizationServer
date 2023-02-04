package com.example.authorizationserver.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByIdAndPass(String id, String pass);
}
