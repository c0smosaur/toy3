package com.core.toy3.src.like.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.like.model.request.LikeRequest;
import com.core.toy3.src.like.model.response.LikeResponse;
import com.core.toy3.src.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 좋아요 추가 또는 취소
    @PostMapping
    public Response<LikeResponse> createAndCancelLike(@RequestBody LikeRequest likeRequest) {
        return likeService.createAndCancelLike(likeRequest);
    }

    // 특정 사용자가 좋아요를 누른 여행 목록 조회
    @GetMapping("/{memberId}")
    public Response<List<LikeResponse>> getLikedTravelsByMemberId(@PathVariable Long memberId) {
        return likeService.getLikedTravelsByMemberId(memberId);
    }

}
