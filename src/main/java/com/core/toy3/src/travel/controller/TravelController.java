package com.core.toy3.src.travel.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.travel.model.request.TravelRequest;
import com.core.toy3.src.travel.model.response.TravelResponse;
import com.core.toy3.src.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travel")
public class TravelController {

    private final TravelService travelService;

    @PostMapping("/create")
    public Response<TravelResponse> createTravel(@RequestBody TravelRequest travelRequest) {

        return Response.response(travelService.createTravel(travelRequest));
    }

    @GetMapping("/read")
    public Response<List<TravelResponse>> getAllTravel() {

        List<TravelResponse> travelResponseList = travelService.getAllravel();

        return Response.response(travelResponseList);
    }

    @GetMapping("/read/{travelId}")
    public Response<TravelResponse> selectTravel(@PathVariable("travelId") Long id) {

        return travelService.selectTravel(id);
    }
}
