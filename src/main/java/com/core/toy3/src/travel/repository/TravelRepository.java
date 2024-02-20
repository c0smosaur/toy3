package com.core.toy3.src.travel.repository;

import com.core.toy3.common.constant.State;
import com.core.toy3.src.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
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
            where tv.state = :state
            """)
    List<Travel> getAllTravelActive(@Param("state") State state);

//    Optional<Travel>

}
