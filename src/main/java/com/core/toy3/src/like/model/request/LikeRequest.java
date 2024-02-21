package com.core.toy3.src.like.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequest {
    private Long travelId;
    private Long memberId;
}
