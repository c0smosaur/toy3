package com.core.toy3.src.member.service;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberRepository memberRepository;

  @Test
  void memberRegisterTest() throws Exception{
    memberRepository.deleteAll();

    MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
            "aaa@aaa.com",
            "1234",
            "aaa");

    Member member = memberService.memberRegister(memberJoinRequest);
    MemberResponse memberResponse = MemberResponse.fromEntity(member);
    assertTrue(memberResponse.getUsername().equals(memberJoinRequest.getUsername()));
  }
}