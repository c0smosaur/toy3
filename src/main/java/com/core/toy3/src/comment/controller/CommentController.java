package com.core.toy3.src.comment.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.model.response.CommentResponse;
import com.core.toy3.src.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;


@RestController
@RequestMapping("/comments")
//@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> saveComment(
            @RequestParam(name = "travel_id") Long travelId,
            @RequestBody CommentRequest commentRequest,
            HttpServletRequest request
    ) {
        Principal principal = request.getUserPrincipal();
        if (principal == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userName = principal.getName();

        Response<CommentResponse> response = commentService.createComment(travelId, commentRequest);

        return ResponseEntity.status(response.getStatus()).body(response.getData());

//        return ResponseEntity.created(URI.create("/travels/"+travelId)).build();
    }

    /*@PostMapping("/posts")
    @Operation(summary = "댓글 생성", description = "댓글을 생성합니다.")
    public ResponseEntity<HttpStatus> saveComment(
            @Parameter(description = "여행 ID", required = true, example = "1")
            @PathVariable(name = "travel_id") int travelId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal MemberAdapter memberAdapter
    ) {
        commentService.createComment(travelId, request, memberAdapter);

//        return ResponseEntity.noContent().build();
        return ResponseEntity.created(URI.create("/travels/"+travelId)).build();
    }*/


}
