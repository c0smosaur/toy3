package com.core.toy3.src.travel.model.response;

import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.travel.entity.Travel;

import com.core.toy3.common.constant.State;
import com.core.toy3.src.travel.entity.Travel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TravelResponse {
    private int likeCount;
    public TravelResponse(int likeCount) {
        this.likeCount = likeCount;
    }

    public static TravelResponse fromEntity(Travel travel, Member member) {
        return null;
    }

    private Long id;
    private String travelName;
    private State state;
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public static TravelResponse toResult(Travel travel) {
        return TravelResponse.builder()
                .id(travel.getId())
                .travelName(travel.getTravelName())
                .state(travel.getState())
                .departure(travel.getDeparture())
                .arrival(travel.getArrival())
                .departureTime(travel.getDepartureTime())
                .arrivalTime(travel.getArrivalTime())
                .build();
    }


}
