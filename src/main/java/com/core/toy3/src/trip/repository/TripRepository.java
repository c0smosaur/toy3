package com.core.toy3.src.trip.repository;

import com.core.toy3.src.trip.entity.Trip;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("""
            select t
            from Trip t
            where t.state = 'ACTIVE'
            and t.travel.id = :travelId
            """)
    List<Trip> getAllTripActiveFromTravel(@Param("travelId") Long id);

    @Query("""
            select t
            from Trip t
            where t.id = :tripId
            and t.travel.id = :travelId
            and t.state = 'ACTIVE'
            """)
    Optional<Trip> getTripById(@Param("travelId") Long travelId, @Param("tripId") Long tripId);

    @Modifying
    @Query("""
            update Trip t
            set t.state = 'DELETE'
            where t.id = :tripId
            and t.state = 'ACTIVE'
            """)
    Optional<Integer> deleteTripById(@Param("tripId") Long id);
}