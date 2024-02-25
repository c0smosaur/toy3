package com.core.toy3.src.like.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.comment.controller.CommentController;
import com.core.toy3.src.like.entity.UserLike;
import com.core.toy3.src.like.model.request.LikeRequest;
import com.core.toy3.src.like.model.response.LikeResponse;
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

class LikeControllerTest {

  @Autowired
  MemberController memberController;
  @Autowired
  TravelController travelController;
  @Autowired
  LikeController likeController;

  MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
          "aaa@aaa.com",
          "1234",
          "aaa");

  MemberRequest memberRequest = new MemberRequest(memberJoinRequest.getUsername(),
          memberJoinRequest.getPassword());

  TravelRequest travelRequest = TravelRequest.builder()
          .travelName("test travel")
          .departure("서울")
          .arrival("여수")
          .departureTime(LocalDateTime.now())
          .arrivalTime(LocalDateTime.now().plusDays(1))
          .build();

  LikeRequest likeRequest = new LikeRequest(1L, 1L);

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
  void createAndCancelLike() {
    AuthMember member = (AuthMember) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    Response<LikeResponse> like = likeController.createAndCancelLike(member, likeRequest);
    assertEquals(1L, (long) like.getData().getTravelId());
    likeController.createAndCancelLike(member, likeRequest);
  }

  @Test
  void getLikedTravelsByMemberId() {
    AuthMember member = (AuthMember) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();
    likeController.createAndCancelLike(member, likeRequest);
    Response<List<LikeResponse>> list = likeController.getLikedTravelsByMemberId(member);
    assertEquals(1, list.getData().size());

  }
}