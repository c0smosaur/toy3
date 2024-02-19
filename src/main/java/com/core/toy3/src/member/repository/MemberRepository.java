package com.core.toy3.src.member.repository;

import com.core.toy3.src.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
