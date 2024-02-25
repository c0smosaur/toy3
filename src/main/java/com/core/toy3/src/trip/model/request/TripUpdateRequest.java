package com.core.toy3.src.trip.model.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripUpdateRequest {

    private String location;
    private LocalDateTime postedAt;

    public void setLocation(String location){
        this.location = location;
    }
}
