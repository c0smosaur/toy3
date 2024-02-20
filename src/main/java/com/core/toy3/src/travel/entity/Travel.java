package com.core.toy3.src.travel.entity;

import com.core.toy3.common.constant.State;
import com.core.toy3.common.entity.BaseEntity;
import com.core.toy3.src.travel.model.request.TravelRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Travel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String travelName;

    @Enumerated(EnumType.STRING)
    private State state;

    private String departure;
    private String arrival;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public static Travel fromRequest(TravelRequest travelRequest) {
        return Travel.builder()
                .travelName(travelRequest.getTravelName())
                .state(travelRequest.getState())
                .departure(travelRequest.getDeparture())
                .arrival(travelRequest.getArrival())
                .departureTime(travelRequest.getDepartureTime())
                .arrivalTime(travelRequest.getArrivalTime())
                .build();

    }
}

