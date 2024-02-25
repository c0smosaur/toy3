package com.core.toy3.src.comment.service;

import com.core.toy3.src.member.controller.MemberController;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.service.MemberService;
import com.core.toy3.src.travel.controller.TravelController;
import com.core.toy3.src.travel.model.request.TravelRequest;
import com.core.toy3.src.travel.service.TravelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {

  @Autowired
  MemberController memberController;
  @Autowired
  TravelController travelController;

  @BeforeEach
  public void setUp() throws Exception{
    MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
            "aaa@aaa.com",
            "1234",
            "aaa");

    MemberRequest memberRequest = new MemberRequest(memberJoinRequest.getUsername(),
            memberJoinRequest.getPassword());

    AuthMember authentication = (AuthMember) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

    TravelRequest travelRequest = TravelRequest.builder()
            .travelName("test travel")
            .departure("서울")
            .arrival("여수")
            .departureTime(LocalDateTime.now())
            .arrivalTime(LocalDateTime.now().plusDays(1))
            .build();

    memberController.signUp(memberJoinRequest);
    memberController.login(memberRequest);
    travelController.createTravel(travelRequest, authentication);
  }

  @Test
  void createComment() {
  }

  @Test
  void selectAllCommentByMe() {
  }
}