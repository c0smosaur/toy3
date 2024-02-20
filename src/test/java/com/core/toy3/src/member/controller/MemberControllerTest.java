package com.core.toy3.src.member.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import com.core.toy3.src.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberControllerTest {

  @Autowired
  private MemberController controller;

  @Autowired
  private MemberService service;

  @Autowired
  private final HttpSession session = new MockHttpSession();

  // 임의의 member request
  MemberRequest memberRequest = new MemberRequest(
          "bbb@bbb.com",
          "5678",
          "bbb");

  // 임의의 member response
  MemberResponse memberResponse = new MemberResponse(
          1L,
          "bbb@bbb.com",
          "bbb");

  // 전체 실행시 테스트 순서 랜덤이기 때문에 개별 테스트 진행
  @Test
  public void signUpTest() throws Exception {
        Response<MemberResponse> response = controller.signUp(memberRequest);

    assertEquals(memberResponse.toString(), response.getData().toString()); // Assuming Response has a getData() method.
  }

  @Test
  public void loginTest() throws Exception {
    controller.signUp(memberRequest);

    Response<MemberResponse> response = controller.login(memberRequest, session);

    assertEquals(memberResponse.toString(), response.getData().toString()); // Assuming Response has a getData() method.
    assertNotNull(session.getAttribute("member"));
  }

  @Test
  public void logoutTest() throws Exception {
    Response<String> response = controller.logout(session);

    assertEquals("Logged out", response.getData()); // Assuming Response has a getData() method.
    assertNull(session.getAttribute("member"));
  }
}