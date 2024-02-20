package com.core.toy3.src.like.model.response;

import com.core.toy3.src.like.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class LikeResponse {
    private Integer likeId;
    private Integer memberId;
    private Integer travelId;

    public static LikeResponse fromEntity(Like like) {
        return LikeResponse.builder()
                .likeId(like.getId().intValue())
                .memberId(like.getMember().getId().intValue())
                .travelId(like.getTravel().getId().intValue())
                .build();
    }
}

