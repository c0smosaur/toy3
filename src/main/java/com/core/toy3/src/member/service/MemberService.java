package com.core.toy3.src.member.service;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberJoinRequest;
import com.core.toy3.src.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.core.toy3.common.response.ResponseStatus.DUPLICATE_USERNAME;

@Service
@RequiredArgsConstructor
public class MemberService {

  @Autowired
  private final MemberRepository memberRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Transactional
  public Member memberRegister(MemberJoinRequest memberJoinRequest) throws Exception{
    // 이미 존재하는 계정인지 확인
    Optional<Member> checkDuplicate = memberRepository.findByUsername(memberJoinRequest.getUsername());
    if (checkDuplicate.isPresent()){
      throw new CustomException(DUPLICATE_USERNAME);
    }

    String hashedPassword = passwordEncoder.encode(memberJoinRequest.getPassword());
    memberJoinRequest.setPassword(hashedPassword);

    Member member = Member.fromJoinRequest(memberJoinRequest);

    return memberRepository.save(member);
  }
}
