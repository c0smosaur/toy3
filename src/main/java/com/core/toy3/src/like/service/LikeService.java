package com.core.toy3.src.like.service;

import com.core.toy3.src.like.entity.Like;
import com.core.toy3.src.like.model.request.LikeRequest;
import com.core.toy3.src.like.model.response.LikeResponse;
import com.core.toy3.src.like.repository.LikeRepository;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.travel.entity.Travel;
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
        Member member = memberRepository.findById(likeRequest.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + likeRequest.getMemberId()));

        Travel travel = travelRepository.findById(likeRequest.getTravelId())
                .orElseThrow(() -> new RuntimeException("Travel not found with ID: " + likeRequest.getTravelId()));

        Like like = Like.createLike(member, travel);
        likeRepository.save(like);

        return LikeResponse.fromEntity(like);
    }
}
