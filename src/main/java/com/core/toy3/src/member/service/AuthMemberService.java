package com.core.toy3.src.member.service;

import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AuthMemberService implements UserDetailsService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  public UserDetails getMember(){
    Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

    if (authentication.isAuthenticated()){
      return (UserDetails) authentication.getPrincipal();
    }
    return null;
  }

  public Authentication getAuthentication(MemberRequest memberRequest){
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    memberRequest.getUsername(),
                    memberRequest.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return authentication;
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
