package com.core.toy3.src.travel.service;

import com.core.toy3.common.constant.State;
import com.core.toy3.common.exception.CustomException;
import com.core.toy3.common.response.Response;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.travel.model.request.TravelRequest;
import com.core.toy3.src.travel.model.response.TravelResponse;
import com.core.toy3.src.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import com.core.toy3.common.exception.CustomException;
import com.core.toy3.src.travel.model.request.TravelRequest;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import static com.core.toy3.common.response.ResponseStatus.*;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    @Transactional
    public TravelResponse createTravel(TravelRequest travelRequest) {

        Travel travel = getTravelFromRequest(travelRequest);

        return TravelResponse.toResult(travelRepository.save(travel));
    }

    private Travel getTravelFromRequest(TravelRequest travelRequest) {

        validateTravelDepartureTime(travelRequest);

        return Travel.builder()
                .travelName(travelRequest.getTravelName())
                .state(State.ACTIVE)
                .departure(travelRequest.getDeparture())
                .arrival(travelRequest.getArrival())
                .departureTime(travelRequest.getDepartureTime())
                .arrivalTime(travelRequest.getArrivalTime())
                .build();
    }

    private void validateTravelDepartureTime(TravelRequest travelRequest) {

        LocalDateTime arrivalTime = travelRequest.getArrivalTime();

        if(!arrivalTime.isAfter(travelRequest.getDepartureTime()) ||
           !arrivalTime.isAfter(LocalDateTime.now())) {

            throw new CustomException(ARRIVALTIME_IS_INCORRECT);
        }
    }

    @Transactional
    public List<TravelResponse> getAllravel() {
        // state가 ACTIVE인 데이터만 조회
        List<Travel> travelActive = travelRepository.getAllTravelActive();

        // Travel엔티티 list를 stream을 활용해 TravelResponse로 구성해서 리턴
        return travelActive.stream()
                .map(TravelResponse::toResult)
                .collect(Collectors.toList());
    }

    @Transactional
    public TravelResponse selectTravel(Long id) {

        Travel travel = travelRepository.getTravelActive(id)
                .orElseThrow(() -> new CustomException(TRAVEL_DOES_NOT_EXIST));

        return TravelResponse.toResult(travel);
    }

    @Transactional
    public TravelResponse updateTravel(long id, TravelRequest travelRequest) {

        Travel travel = travelRepository.getTravelActive(id)
                .orElseThrow(() -> new CustomException(TRAVEL_DOES_NOT_EXIST));

        travel.update(travelRequest);

        return TravelResponse.toResult(travel);
    }

    @Transactional
    public Integer deleteTravel(Long id) {

        return travelRepository.deleteTravelById(id)
                .filter(result -> result == 1) // 1이 return 될 경우 삭제완료
                .orElseThrow(() -> new CustomException(DELETE_IS_FAIL));

    }
    public Response<TravelResponse> getTravelDetails(Long id) {
        return travelRepository.findById(id)
                .map(travel -> Response.response(new TravelResponse(travel.getLikeCount())))
                .orElse(null);
    }
}
