package com.core.toy3.src.like.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.like.model.request.LikeRequest;
import com.core.toy3.src.like.model.response.LikeResponse;
import com.core.toy3.src.like.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // 좋아요 추가 또는 취소
    @PostMapping
    public Response<LikeResponse> createAndCancelLike(@RequestBody LikeRequest likeRequest) {
        return likeService.createAndCancelLike(likeRequest);
    }

}
