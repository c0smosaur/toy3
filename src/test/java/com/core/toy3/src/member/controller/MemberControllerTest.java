package com.core.toy3.src.member.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import com.core.toy3.src.member.service.AuthMemberService;
import com.core.toy3.src.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberControllerTest {

  @Autowired
  private MemberController controller;
  @Autowired
  private MemberService service;
  @Autowired
  private AuthMemberService authMemberService;
  @Autowired
  private final HttpSession session = new MockHttpSession();

  // 임의의 member request
  MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
          "bbb@bbb.com",
          "5678",
          "bbb");

  // 임의의 member login request
  MemberRequest memberRequest = new MemberRequest(
          "bbb@bbb.com",
          "5678"
  );

  @Test
  public void signUpTest() throws Exception {
    Response<MemberResponse> response = controller.signUp(memberJoinRequest);

    assertEquals(memberJoinRequest.getUsername(),
            response.getData().getUsername());
  }

}