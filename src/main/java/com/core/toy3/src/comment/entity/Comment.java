package com.core.toy3.src.comment.entity;

import com.core.toy3.common.entity.BaseEntity;
import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    private String content;

    protected Comment() {
    }

    /*private Comment(Member member, Travel travel, String content){
        this.member = member;
        this.travel = travel;
        this.content = content;
    }

    public static Comment of(Member member, Travel travel, String content){
        return new Comment(member, travel, content);
    }*/

    public static Comment createComment(Member member, Travel travel, String content){
        return Comment.builder()
                .member(member)
                .travel(travel)
                .content(content)
                .build();
    }

    public void updateComment(CommentRequest request){
        this.content = request.getContent();
    }
}
