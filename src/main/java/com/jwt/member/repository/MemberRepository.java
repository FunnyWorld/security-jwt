package com.jwt.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
