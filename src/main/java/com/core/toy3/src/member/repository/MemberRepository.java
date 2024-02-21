package com.core.toy3.src.member.repository;

import com.core.toy3.src.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  public Optional<Member> findByUsername(String username);

  public Optional<Member> findByUsernameAndPassword(String username, String password);

}
