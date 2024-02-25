package com.core.toy3.src.travel.controller;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.common.response.Response;
import com.core.toy3.common.response.ResponseStatus;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.travel.model.request.TravelRequest;
import com.core.toy3.src.travel.model.response.TravelResponse;
import com.core.toy3.common.KakaoMapLocation;
import com.core.toy3.src.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

        List<TravelResponse> travelResponseList = travelService.getAllravel();

        return Response.response(travelResponseList);
    }

    @GetMapping("/read/{travelId}") // 1
    public Response<TravelResponse> selectTravel(@PathVariable("travelId") Long id) {

        return Response.response(travelService.selectTravel(id));
    }

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
