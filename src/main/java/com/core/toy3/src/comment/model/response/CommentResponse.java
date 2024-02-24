package com.core.toy3.src.comment.model.response;

import com.core.toy3.src.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private Long memberId;
    private Integer travelId;
    private String content;


    public static CommentResponse fromEntity(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .memberId(comment.getMember().getId())
                .content(comment.getContent())
                .build();

    }
}
