package com.core.toy3.src.comment.model.request;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private Long memberId;
    private Long travelId;
    private String content;
}
