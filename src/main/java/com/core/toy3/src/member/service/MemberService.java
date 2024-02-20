package com.core.toy3.src.member.service;

import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  @Autowired
  private final MemberRepository memberRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Member memberRegister(MemberJoinRequest memberJoinRequest) throws Exception{
    String hashedPassword = passwordEncoder.encode(memberJoinRequest.getPassword());
    memberJoinRequest.setPassword(hashedPassword);

    Member member = MemberJoinRequest.toEntity(memberJoinRequest);

    return memberRepository.save(member);
  }
}
