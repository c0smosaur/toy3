package com.core.toy3.src.member.model.request;


import com.core.toy3.src.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
  private String username;
  private String password;
  private String name;

  public static Member toEntity(MemberRequest memberRequest){
    return Member.builder()
            .username(memberRequest.getUsername())
            .password(memberRequest.getPassword())
            .name(memberRequest.getName())
            .build();
  }
}
