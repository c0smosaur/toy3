package com.core.toy3.src.travel.repository;

import com.core.toy3.common.constant.State;
import com.core.toy3.src.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query("""
            select tv
            from Travel tv
            where tv.state = 'ACTIVE'
            """)
    List<Travel> getAllTravelActive();

    @Query("""
            select tv
            from Travel tv
            where tv.id = :travel_id
            and tv.state = 'ACTIVE'
            """)
    Optional<Travel> getTravelActive(@Param("travel_id") Long id);

    @Modifying
    @Query("""
            update Travel tv
            set tv.state = 'DELETE'
            where tv.id = :travel_id
            and tv.state = 'ACTIVE'
            """)
    Optional<Integer> deleteTravelById(@Param("travel_id") Long id);
}
