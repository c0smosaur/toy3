package com.core.toy3.src.comment.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.comment.entity.Comment;
import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.model.response.CommentResponse;
import com.core.toy3.src.comment.service.CommentService;
import com.core.toy3.src.member.entity.AuthMember;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/comments")
//@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts")
    public Response<CommentResponse> saveComment(
            @AuthenticationPrincipal AuthMember member,
            @RequestBody CommentRequest commentRequest
    ) {
        Comment comment = commentService.createComment(member, commentRequest);

        return Response.response(CommentResponse.fromEntity(comment));

    }

    @GetMapping("/list")
    public List<CommentResponse> viewComments(
            @AuthenticationPrincipal AuthMember member) {

        List<Comment> commentList = commentService.selectAllCommentByMe(member);
        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseList.add(CommentResponse.fromEntity(comment));
        }
        return commentResponseList;
    }

}
