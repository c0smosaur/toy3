package com.core.toy3.src.member.model.request;

import com.core.toy3.src.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinRequest {

  @Email
  @NotBlank
  private String username;
  @Min(4)
  @NotBlank
  private String password;
  @NotBlank
  private String name;

  public static Member toEntity(MemberJoinRequest memberJoinRequest){
    return Member.builder()
            .username(memberJoinRequest.getUsername())
            .password(memberJoinRequest.getPassword())
            .name(memberJoinRequest.getName())
            .build();
  }
}
