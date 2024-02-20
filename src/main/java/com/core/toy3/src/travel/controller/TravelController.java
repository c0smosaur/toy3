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

        return Response.response(travelService.selectTravel(id));
    }

    @PutMapping("/update-travel/{travelId}")
    public Response<TravelResponse> updateTravel(
            @PathVariable("travelId") long id,
            @RequestBody TravelRequest travelRequest) {

            return Response.response(travelService.updateTravel(id, travelRequest));
    }

    @DeleteMapping("/delete-travel/{travelId}")
    public Response<String> deleteTravel(@PathVariable("travelId") Long id) {

        Integer result = travelService.deleteTravel(id);

        String resultMessage = result == 1 ? "삭제가 완료되었습니다." : "삭제에 실패했습니다.";

        return Response.response(resultMessage);
    }
}
