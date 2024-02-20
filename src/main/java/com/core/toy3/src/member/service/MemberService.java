package com.core.toy3.src.member.service;

import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.model.request.MemberRequest;
import com.core.toy3.src.member.model.response.MemberResponse;
import com.core.toy3.src.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  @Autowired
  private final MemberRepository repository;

  public MemberResponse memberRegister(MemberRequest memberRequest) throws Exception{
    Member member = repository.save(MemberRequest.toEntity(memberRequest));
    return MemberResponse.fromEntity(member);
  }

  public MemberResponse memberLogin(String username, String password) throws Exception{
    Member member = repository.findByUsernameAndPassword(username,password);
    return MemberResponse.fromEntity(member);
  }
}
