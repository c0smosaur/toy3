package com.core.toy3.src.member.service;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@Lazy
public class AuthMemberService implements UserDetailsService {

  @Autowired
  private MemberRepository memberRepository;

  public UserDetails getMember(){
    Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

    // 인증 x면 접근 불가능, authenticated 확인할 필요 x
      String username = authentication.getName();
      return loadUserByUsername(username);
  }

  public MemberResponse getMemberResponse(){
    Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

    return MemberResponse.fromAuthentication(authentication);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Member> optional = memberRepository.findByUsername(username);
    if (optional.isEmpty()) {
      throw new UsernameNotFoundException(username + " is not found");
    }

    Member member = optional.get();

    return new AuthMember(member);
  }
}
