package com.core.toy3.src.comment.model.response;

import com.core.toy3.src.comment.entity.Comment;
import lombok.*;


@Getter
//@Builder
@ToString
@NoArgsConstructor
public class CommentResponse {
    private Integer id;
    private Integer memberId;
    private Integer travelId;
    private String content;


//    public static CommentResponse fromEntity(Comment comment){
////        return CommentResponse.builder()
//
//    }
}
