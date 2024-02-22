package com.core.toy3.src.comment.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class CommentRequest {
    private Integer id;
    private Integer memberId;
    private Integer travelId;
    private String content;
    public CommentRequest(String content){
        this.content = content;
    }
}
