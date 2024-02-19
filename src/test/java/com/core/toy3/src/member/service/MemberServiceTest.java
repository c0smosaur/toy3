package com.core.toy3.src.member.service;

import com.core.toy3.common.exception.CustomException;
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
  private MemberService service;

  @Autowired
  private MemberRepository repository;

  MemberRequest memberRequest = new MemberRequest(
          "aaa@aaa.com",
          "1234",
          "aaa");

  @Test
  void memberRegisterTest() throws Exception{
    repository.deleteAll();
    MemberResponse memberResponse = service.memberRegister(memberRequest);
    assertTrue(memberResponse.getUsername().equals(memberRequest.getUsername()));

  }

  @Test
  void memberLoginTest() throws Exception {
    repository.deleteAll();

    MemberResponse member = service.memberRegister(memberRequest);

    MemberRequest memberRequest1 = new MemberRequest("aaa@aaa.com","1234","login");
    MemberResponse loggedMember = service.memberLogin(memberRequest1.getUsername(), memberRequest1.getPassword());

    assertTrue(loggedMember.getUsername().equals(memberRequest.getUsername()));
  }
}