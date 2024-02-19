package com.core.toy3.src.like.model.request;

public class LikeRequest {
    private Long memberId;
    private Long travelId;
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

}
