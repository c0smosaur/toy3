package com.core.toy3.src.like.entity;

import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    public static Like createLike(Member member, Travel travel) {
        return Like.builder()
                .member(member)
                .travel(travel)
                .build();

    }
}
