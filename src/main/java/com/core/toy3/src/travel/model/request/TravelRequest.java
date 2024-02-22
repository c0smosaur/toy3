package com.core.toy3.src.travel.model.request;

import com.core.toy3.common.constant.State;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TravelRequest {

    private String travelName;
    private State state;
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
