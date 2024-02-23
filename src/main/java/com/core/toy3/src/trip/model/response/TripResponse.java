package com.core.toy3.src.trip.model.response;

import com.core.toy3.common.constant.State;
import com.core.toy3.src.trip.entity.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TripResponse {

    private Long id;
    private String location;
    private State state;
    private LocalDateTime postedAt;

    public static TripResponse toResult(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .location(trip.getLocation())
                .state(trip.getState())
                .postedAt(trip.getPostedAt())
                .build();
    }
}
