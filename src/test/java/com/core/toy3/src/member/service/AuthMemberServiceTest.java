package com.core.toy3.src.member.service;

import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthMemberServiceTest {

  @Autowired
  AuthMemberService authMemberService;
  @Autowired
  MemberService memberService;

  @Test
  public void loadUserByUsernameTest() throws Exception {
    MemberJoinRequest memberJoinRequest = new MemberJoinRequest("aaa@aaa.com","1234","aaa");
    Member member = memberService.memberRegister(memberJoinRequest);

    UserDetails userDetails = authMemberService.loadUserByUsername(member.getUsername());
    assertEquals(userDetails.getUsername(), member.getUsername());

    assertThrows(UsernameNotFoundException.class,
            () -> authMemberService.loadUserByUsername("bbb@bbb.com"));
  }

}