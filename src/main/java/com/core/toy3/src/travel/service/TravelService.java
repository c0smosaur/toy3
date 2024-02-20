package com.core.toy3.src.travel.service;

import com.core.toy3.common.constant.State;
import com.core.toy3.common.response.Response;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.travel.model.request.TravelRequest;
import com.core.toy3.src.travel.model.response.TravelResponse;
import com.core.toy3.src.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    @Transactional
    public TravelResponse createTravel(TravelRequest travelRequest) {

        return TravelResponse.toResult(
                travelRepository.save(
                        Travel.fromRequest(travelRequest)));
    }


    public List<TravelResponse> getAllravel() {
        // state가 ACTIVE인 데이터만 조회
        List<Travel> travelActive = travelRepository.getAllTravelActive(State.ACTIVE);

        // Travel엔티티 list를 stream을 활용해 TravelResponse로 구성해서 리턴
        return travelActive.stream()
                .map(TravelResponse::toResult)
                .collect(Collectors.toList());
    }

    public Response<TravelResponse> selectTravel(Long id) {
        return null;
    }
}
