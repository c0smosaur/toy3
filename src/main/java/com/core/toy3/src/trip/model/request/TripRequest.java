package com.core.toy3.src.trip.model.request;

import com.core.toy3.common.constant.State;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripRequest {

    private String location;
    private State state;
    private LocalDateTime postedAt;

    public void setLocation(String location) {
        this.location = location;
    }
}
