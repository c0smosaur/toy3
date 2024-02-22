package com.core.toy3.src.member.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class AuthMember extends User {

  private Long id;
  private Member member;

  public AuthMember(Member member){
    super(member.getUsername(),
            member.getPassword(),
            getAuthorities(member));
    this.id = member.getId();
//    this.member = member;
  }

  private static Collection<GrantedAuthority> getAuthorities(Member member){
    // role 사용 x
    return Collections.emptyList();
  }

}
