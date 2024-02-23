package com.core.toy3.src.travel.model.response;

import com.core.toy3.common.constant.State;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.trip.model.response.TripResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TravelResponse {

    private Long id;
    private String travelName;
    private State state;
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<TripResponse> trip;

    public static TravelResponse toResult(Travel travel) {
        return TravelResponse.builder()
                .id(travel.getId())
                .travelName(travel.getTravelName())
                .state(travel.getState())
                .departure(travel.getDeparture())
                .arrival(travel.getArrival())
                .departureTime(travel.getDepartureTime())
                .arrivalTime(travel.getArrivalTime())
                .trip(travel.getTrip()
                        .stream()
                        .map(TripResponse::toResult)
                        .collect(Collectors.toList()))
                .build();
    }
}
