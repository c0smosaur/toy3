package com.core.toy3.src.travel.service;

import com.core.toy3.common.response.Response;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.travel.model.response.TravelResponse;
import com.core.toy3.src.travel.repository.TravelRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelService {
    private final TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public Response<TravelResponse> getTravelDetails(Long travelId) {
        return travelRepository.findById(travelId)
                .map(travel -> Response.response(new TravelResponse(travel.getLikeCount())))
                .orElse(null);
    }
}