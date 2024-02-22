package com.core.toy3.src.comment.controller;

import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.service.CommentService;
import com.core.toy3.src.member.entity.MemberAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts")
    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    public ResponseEntity<HttpStatus> saveConmment(
            @Parameter(description = "여행 ID", required = true, example = "1")
            @PathVariable(name = "travel_id") int travelId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal MemberAdapter memberAdapter
    ) {
        commentService.createComment(travelId, request, memberAdapter);

//        return ResponseEntity.noContent().build();
        return ResponseEntity.created(URI.create("/travels/"+travelId)).build();
    }


}
