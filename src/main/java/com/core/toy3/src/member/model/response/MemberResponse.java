package com.core.toy3.src.member.model.response;

import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.Authentication;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class MemberResponse {
  private Long id;
  private String username;
  private String name;

  public static MemberResponse fromEntity(Member member){
    return MemberResponse.builder()
            .id(member.getId())
            .username(member.getUsername())
            .name(member.getName())
            .build();
  }

  public static MemberResponse fromAuthentication(Authentication authentication){
    AuthMember authMember = (AuthMember) authentication.getPrincipal();
    return MemberResponse.builder()
            .id(authMember.getId())
            .username(authMember.getUsername())
            .name(authMember.getMember().getName())
            .build();
  }
}
