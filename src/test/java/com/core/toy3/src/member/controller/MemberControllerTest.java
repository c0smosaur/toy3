package com.core.toy3.src.member.controller;

import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import com.core.toy3.src.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberControllerTest {

  @Autowired
  MemberController controller;

  @Autowired
  MemberService service;

  @Autowired
  MemberRepository repository;

  MemberRequest memberRequest = new MemberRequest(
          "bbb@bbb.com",
          "5678",
          "bbb");

  @Test
  void logout(MemberRequest memberRequest, HttpSession session) throws Exception {
  }
}