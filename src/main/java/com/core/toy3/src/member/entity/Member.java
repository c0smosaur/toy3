package com.core.toy3.src.member.entity;

import com.core.toy3.common.entity.BaseEntity;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity(name="member")
public class Member extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;  // email
  private String password;
  private String name;

  public static Member fromJoinRequest(MemberJoinRequest memberJoinRequest){
    return Member.builder()
            .username(memberJoinRequest.getUsername())
            .password(memberJoinRequest.getPassword())
            .name(memberJoinRequest.getName())
            .build();
  }
}
