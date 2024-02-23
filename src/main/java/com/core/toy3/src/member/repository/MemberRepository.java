package com.core.toy3.src.member.repository;

import com.core.toy3.src.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
  public Member findByUsernameAndPassword(String username, String password);
}
