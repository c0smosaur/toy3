package com.core.toy3.src.trip.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.trip.model.request.TripRequest;
import com.core.toy3.src.trip.model.request.TripUpdateRequest;
import com.core.toy3.src.trip.model.response.TripResponse;
import com.core.toy3.src.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trip")
public class TripController {

    private final TripService tripService;

    @PostMapping("{travelId}/create") // 1
    public Response<TripResponse> createTrip(@PathVariable("travelId") Long id,
                                             @RequestBody TripRequest tripRequest,
                                             @AuthenticationPrincipal AuthMember authMember) {

        return Response.response(tripService.createTrip(id, tripRequest, authMember));
    }

    @GetMapping("{travelId}/read") // 1
    public Response<List<TripResponse>> getAllTripFromTravel(@PathVariable("travelId") Long id) {
        List<TripResponse> tripResponseList = tripService.getAllTripFromTravel(id);

        return Response.response(tripResponseList);
    }

    @GetMapping("{travelId}/read/{tripId}")//
    public Response<TripResponse> getTrip(@PathVariable("travelId") Long travelId,
                                          @PathVariable("tripId") Long tripId) {

        return Response.response(tripService.getTrip(travelId, tripId));
    }

    @PutMapping("/{travelId}/trip-update/{tripId}")
    public Response<TripResponse> updateTrip(@PathVariable("tripId") Long tripId,
                                             @PathVariable("travelId") Long travelId,
                                             @RequestBody TripUpdateRequest request,
                                             @AuthenticationPrincipal AuthMember authMember) {

        return Response.response(tripService.updateTrip(tripId, travelId, request, authMember));
    }

    @DeleteMapping("/{travelId}/trip-delete/{tripId}")
    public Response<String> deleteTrip(@PathVariable("tripId") Long tripId,
                                       @PathVariable("travelId") Long travelId,
                                       @AuthenticationPrincipal AuthMember authMember) {

        Integer result = tripService.deleteTrip(tripId, travelId, authMember);

        String resultMessage = result == 1 ? "삭제가 완료되었습니다." : "삭제에 실패했습니다.";

        return Response.response(resultMessage);
    }
}
