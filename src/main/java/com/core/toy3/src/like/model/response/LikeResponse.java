package com.core.toy3.src.like.model.response;

import com.core.toy3.src.like.entity.Like;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class LikeResponse {
    private Long likeId;
    private Long travelId;

    public static LikeResponse fromEntity(Like like) {
        return LikeResponse.builder()
                .likeId(like.getId())
                .travelId(like.getTravel().getId())
                .build();
    }
}

