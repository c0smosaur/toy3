package com.core.toy3.src.trip.service;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.travel.repository.TravelRepository;
import com.core.toy3.src.trip.entity.Trip;
import com.core.toy3.src.trip.model.request.TripRequest;
import com.core.toy3.src.trip.model.request.TripUpdateRequest;
import com.core.toy3.src.trip.model.response.TripResponse;
import com.core.toy3.src.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.core.toy3.common.response.ResponseStatus.*;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TravelRepository travelRepository;
    private final TripRepository tripRepository;

    @Transactional
    public TripResponse createTrip(Long id, TripRequest tripRequest) {

        Travel travel = travelRepository.findTravel(id)
                .orElseThrow(() -> new CustomException(TRIP_DOES_NOT_EXIST));

        Trip trip = fromRequestToTripEntity(travel, tripRequest);

        validPostedAt(travel, trip);

        return TripResponse.toResult(tripRepository.save(trip));
    }

    private Trip fromRequestToTripEntity(Travel travel, TripRequest tripRequest) {

        return Trip.builder()
                .location(tripRequest.getLocation())
                .state(tripRequest.getState())
                .postedAt(tripRequest.getPostedAt())
                .travel(travel)
                .build();
    }

    private void validPostedAt(Travel travel, Trip trip) {

        LocalDateTime postedAt = trip.getPostedAt();

        if(travel.getArrivalTime().isBefore(postedAt) || travel.getDepartureTime().isAfter(postedAt)) {
            throw new CustomException(POSTAT_IS_INCORRECT,
                    """
                    | 여정 시간이 여행의 도착 시간보다 이후의 시간이거나
                    | 여행의 출발 시간보다 이전의 시간으로 설정되어 논리상 맞지 않습니다.
                    """);
        }
    }

    @Transactional
    public List<TripResponse> getAllTrip() {

        List<Trip> tripActive = tripRepository.getAllTripActive();

        return tripActive.stream()
                .map(TripResponse::toResult)
                .collect(Collectors.toList());
    }

    @Transactional
    public TripResponse getTrip(Long id) {

        Trip trip = tripRepository.getTripById(id)
                .orElseThrow(() -> new CustomException(TRIP_DOES_NOT_EXIST));

        return TripResponse.toResult(trip);
    }

    @Transactional
    public TripResponse updateTrip(Long tripid, Long travelId, TripUpdateRequest tripRequest) {

        Travel travel = travelRepository.findTravel(travelId)
                .orElseThrow(() -> new CustomException(TRAVEL_DOES_NOT_EXIST));

        Trip trip = tripRepository.getTripById(tripid)
                .orElseThrow(() -> new CustomException(TRIP_DOES_NOT_EXIST));

        trip.update(tripRequest);
        validPostedAt(travel, trip);

        return TripResponse.toResult(trip);
    }

    @Transactional
    public Integer deleteTrip(Long id) {

        return tripRepository.deleteTripById(id)
                .filter(result -> result == 1)
                .orElseThrow(() -> new CustomException(DELETE_IS_FAIL));

    }
}
