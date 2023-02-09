package com.menstalk.memberservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menstalk.memberservice.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
