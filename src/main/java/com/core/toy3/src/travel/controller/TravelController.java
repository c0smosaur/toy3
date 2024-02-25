package com.core.toy3.src.travel.controller;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.travel.model.request.TravelRequest;
import com.core.toy3.src.travel.model.response.TravelResponse;
import com.core.toy3.common.KakaoMapLocation;
import com.core.toy3.src.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/travel")
public class TravelController {

    private final TravelService travelService;
    private final KakaoMapLocation kakaoMapLocation;

    @PostMapping("/create")// 1
    public Response<TravelResponse> createTravel(@RequestBody TravelRequest travelRequest,
                                                 @AuthenticationPrincipal AuthMember authMember) {

        return Response.response(travelService.createTravel(travelRequest, authMember));
    }

    @GetMapping("/read")// 1
    public Response<List<TravelResponse>> getAllTravel() {

        List<TravelResponse> travelResponseList = travelService.getAllTravel();

        return Response.response(travelResponseList);
    }

    @GetMapping("/read/{travelId}") // 1
    public Response<TravelResponse> selectTravel(@PathVariable("travelId") Long id) {

        return Response.response(travelService.selectTravel(id));
    }

    @GetMapping("/read/search")
    public Response<List<TravelResponse>> searchTravel(
            @RequestParam(value = "travelName", required = false) String travelName,
            @RequestParam(value = "departure", required = false) String departure,
            @RequestParam(value = "arrival", required = false) String arrival) {

        List<TravelResponse> travelSearchList = travelService.getAllTravel(); // 검색 안할경우 전체 리스트 출력

        if (travelName != null && !travelName.trim().isEmpty()) {
            System.out.println("travelName = " + travelName);
            travelSearchList = travelService.SearchTravelByTravelName(travelName);
        }

        if (departure != null && !departure.trim().isEmpty()) {
            System.out.println("departure = " + departure);
            travelSearchList = travelService.SearchTravelByDeparture(departure);
        }

        if (arrival != null && !arrival.trim().isEmpty()) {
            System.out.println("arrival = " + arrival);
            travelSearchList = travelService.SearchTravelByArrival(arrival);
        }

        return Response.response(travelSearchList);
    }

//    @GetMapping("/read/search")
//    public Response<List<TravelResponse>> SearchTravelByTravelName(@RequestParam(value = "travelName") String keyword) {
//
//        List<TravelResponse> travelSearchList = travelService.SearchTravelByTravelName(keyword);
//
//        return Response.response(travelSearchList);
//    }
//
//    @GetMapping("/read/search")
//    public Response<List<TravelResponse>> SearchTravelByDeparture(@RequestParam(value = "departure") String keyword) {
//
//        List<TravelResponse> travelSearchList = travelService.SearchTravelByDeparture(keyword);
//
//        return Response.response(travelSearchList);
//    }
//
//    @GetMapping("/read/search")
//    public Response<List<TravelResponse>> SearchTravelByArrival(@RequestParam(value = "arrival") String keyword) {
//
//        List<TravelResponse> travelSearchList = travelService.SearchTravelByArrival(keyword);
//
//        return Response.response(travelSearchList);
//    }

    @PutMapping("/update-travel/{travelId}") // 1
    public Response<TravelResponse> updateTravel(
            @PathVariable("travelId") long id,
            @RequestBody TravelRequest travelRequest,
            @AuthenticationPrincipal AuthMember authMember) {

        return Response.response(travelService.updateTravel(id, travelRequest, authMember));
    }

    @DeleteMapping("/delete-travel/{travelId}") // 1
    public Response<String> deleteTravel(@PathVariable("travelId") Long id,
                                         @AuthenticationPrincipal AuthMember authMember) {

        Integer result = travelService.deleteTravel(id, authMember);

        String resultMessage = result == 1 ? "삭제가 완료되었습니다." : "삭제에 실패했습니다.";

        return Response.response(resultMessage);
    }
}
