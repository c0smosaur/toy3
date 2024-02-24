package com.core.toy3.src.member.entity;

import com.core.toy3.common.entity.BaseEntity;

import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.response.MemberResponse;

import com.core.toy3.src.like.entity.UserLike;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity(name = "member")
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;  // email
  private String password;
  private String name;
  
    //Like 관계 및 여러 개의 좋아요를 가질 수 있음
  @OneToMany(mappedBy = "member")
  private final List<UserLike> likes = new ArrayList<>();

  public static Member fromJoinRequest(MemberJoinRequest memberJoinRequest){
    return Member.builder()
            .username(memberJoinRequest.getUsername())
            .password(memberJoinRequest.getPassword())
            .name(memberJoinRequest.getName())
            .build();
  }
}


