package com.core.toy3.src.member.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.service.AuthMemberService;
import com.core.toy3.src.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

  @Autowired
  private final MemberService memberService;

  @Autowired
  private AuthMemberService authMemberService;

  @GetMapping("/")
  public Response<String> testPage(){
    return Response.response("default page");
  }

  @GetMapping("/logged")
  public Response<String> loggedPage(){
    return Response.response("logged in");
  }

  @GetMapping("/error")
  public Response<String> errorPage(){
    return Response.response("login error");
  }

  @PostMapping("/sign-up")
  public Response<MemberResponse> signUp(@RequestBody @Valid MemberJoinRequest member) throws Exception {
    Member joinedMember = memberService.memberRegister(member);
    MemberResponse data = MemberResponse.fromEntity(joinedMember);
    return Response.response(data);
  }

  @GetMapping("/member")
  public Response<UserDetails> test(@AuthenticationPrincipal AuthMember member) throws Exception{
    String username = member.getUsername();

    System.out.println(username);
    System.out.println(member.getMember());

    return Response.response(member);
  }
}
