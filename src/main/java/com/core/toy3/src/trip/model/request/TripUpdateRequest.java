package com.core.toy3.src.trip.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripUpdateRequest {

    private String location;
    private LocalDateTime postedAt;
}
