package com.core.toy3.src.travel.model.response;

import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.travel.entity.Travel;

public class TravelResponse {
    private int likeCount;
    public TravelResponse(int likeCount) {
        this.likeCount = likeCount;
    }

    public static TravelResponse fromEntity(Travel travel, Member member) {
        return null;
    }
}
