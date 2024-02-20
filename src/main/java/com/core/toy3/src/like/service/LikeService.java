// LikeService.java
package com.core.toy3.src.like.service;

import com.core.toy3.src.like.entity.Like;
import com.core.toy3.src.like.model.request.LikeRequest;
import com.core.toy3.src.like.model.response.LikeResponse;
import com.core.toy3.src.like.repository.LikeRepository;
import com.core.toy3.src.travel.repository.TravelRepository;
import com.core.toy3.src.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;

    public LikeService(LikeRepository likeRepository, TravelRepository travelRepository, MemberRepository memberRepository) {
        this.likeRepository = likeRepository;
        this.travelRepository = travelRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public LikeResponse createLike(LikeRequest likeRequest) {
        return memberRepository.findById(likeRequest.getMemberId())
                .map(member -> travelRepository.findById(likeRequest.getTravelId())
                        .map(travel -> {
                            Like like = likeRepository.save(new Like(member, travel));
                            return new LikeResponse(like.getId());
                        })
                        .orElseThrow(() -> new RuntimeException("Travel not found with ID: " + likeRequest.getTravelId())))
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + likeRequest.getMemberId()));
    }

}