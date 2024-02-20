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

    @PostMapping
    public Response<LikeResponse> createLike(@RequestBody LikeRequest likeRequest) {
        // likeService.createLike(likeRequest)를 호출하여 생성한 LikeResponse를 반환
        LikeResponse likeResponse = likeService.createLike(likeRequest);
        return Response.response(likeResponse);
    }

}
