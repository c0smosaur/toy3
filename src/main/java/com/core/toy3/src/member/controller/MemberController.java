package com.core.toy3.src.member.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

  @Autowired
  private final MemberService service;

  // 가입
  // 가입 후 새로 로그인 요구 OR 가입하자마자 로그인 유지
  // 우선 결과 확인 위해 MemberResponse 반환하도록 설정, 추후 수정
  @PostMapping("/sign-up")
  public Response<MemberResponse> signUp(@RequestBody @Valid MemberRequest member) throws Exception {
    MemberResponse data = service.memberRegister(member);
    return Response.response(data);
  }

  // 로그인
  // MemberResponse 담은 Response 반환
  @PostMapping("/login")
  public Response<MemberResponse> login(
          @RequestBody @Valid MemberRequest member,
          HttpSession session) throws Exception{
    MemberResponse data = service.memberLogin(member.getUsername(), member.getPassword());

    // 세션에 저장
    session.setAttribute("member",data);
    return Response.response(data);
  }

  // 로그아웃
  // 세션에서 member 삭제, 문자열 담은 Response 반환
  @GetMapping("/logout")
  public Response<String> logout(HttpSession session) throws Exception{
      session.invalidate();
      return Response.response("Logged out");

    // session 존재하지 않을 때 -> spring security로 인가 없을 때는 접근제한
  }
}
