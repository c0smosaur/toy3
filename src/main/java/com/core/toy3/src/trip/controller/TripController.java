package com.core.toy3.src.trip.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.trip.model.request.TripRequest;
import com.core.toy3.src.trip.model.request.TripUpdateRequest;
import com.core.toy3.src.trip.model.response.TripResponse;
import com.core.toy3.src.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @PostMapping("{travelId}/create")
    public Response<TripResponse> createTrip(@PathVariable("travelId") Long id, @RequestBody TripRequest tripRequest) {

        return Response.response(tripService.createTrip(id, tripRequest));
    }

    @GetMapping("/read")
    public Response<List<TripResponse>> getAllTrip() {
        List<TripResponse> tripResponseList = tripService.getAllTrip();

        return Response.response(tripResponseList);
    }

    @GetMapping("/read/{tripId}")
    public Response<TripResponse> getTrip(@PathVariable("tripId") Long id) {

        return Response.response(tripService.getTrip(id));
    }

    @PutMapping("/{travelId}/trip-update/{tripId}")
    public Response<TripResponse> updateTrip(@PathVariable("tripId") Long tripId,
                                             @PathVariable("travelId") Long travelId,
                                             @RequestBody TripUpdateRequest request) {

        return Response.response(tripService.updateTrip(tripId, travelId, request));
    }

    @DeleteMapping("/trip-delete/{tripId}")
    public Response<String> deleteTrip(@PathVariable("tripId") Long id) {

        Integer result = tripService.deleteTrip(id);

        String resultMessage = result == 1 ? "삭제가 완료되었습니다." : "삭제에 실패했습니다.";

        return Response.response(resultMessage);
    }
}
