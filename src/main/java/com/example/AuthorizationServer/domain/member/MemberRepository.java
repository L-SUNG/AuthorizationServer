package com.example.authorizationserver.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByIdAndPass(String id, String pass);

    Optional<Member> findById(String id);
}
