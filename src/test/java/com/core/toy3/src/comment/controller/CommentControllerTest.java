package com.core.toy3.src.comment.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.comment.entity.Comment;
import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.model.response.CommentResponse;
import com.core.toy3.src.member.controller.MemberController;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.travel.controller.TravelController;
import com.core.toy3.src.travel.model.request.TravelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentControllerTest {

  @Autowired
  MemberController memberController;
  @Autowired
  TravelController travelController;
  @Autowired
  CommentController commentController;

  MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
          "aaa@aaa.com",
          "1234",
          "aaa");

  MemberJoinRequest memberJoinRequest1 = new MemberJoinRequest(
          "bbb@bbb.com",
          "1234",
          "bbb"
  );

  MemberRequest memberRequest = new MemberRequest(memberJoinRequest.getUsername(),
          memberJoinRequest.getPassword());
  MemberRequest memberRequest1 = new MemberRequest(memberJoinRequest1.getUsername(),
          memberJoinRequest1.getPassword());

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

  CommentRequest commentRequest = new CommentRequest(
          1L, 1L, "comment test content");

  @BeforeEach
  public void setUp() throws Exception{
    memberController.signUp(memberJoinRequest);
    Authentication authentication = new UsernamePasswordAuthenticationToken(
            memberRequest.getUsername(),memberRequest.getPassword()
    );
    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(authentication);
    SecurityContextHolder.setContext(securityContext);

    AuthMember member = (AuthMember) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    travelController.createTravel(travelRequest, member);
  }
  @Test
  void saveComment() {
    AuthMember member = (AuthMember) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    Response<CommentResponse> comment =
            commentController.saveComment(
                    member,
                    commentRequest);
    assertEquals(1L, comment.getData().getId());
  }

  @Test
  void viewComments() {
    commentController.saveComment(
                    authentication,
                    commentRequest);
    List<CommentResponse> list = commentController.viewComments(authentication);
    assertFalse(list.isEmpty());
  }
}