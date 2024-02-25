package com.core.toy3.src.travel.repository;

import com.core.toy3.common.constant.State;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.travel.entity.Travel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class TravelRepositoryTest {

    @Autowired
    private TravelRepository travelRepository;

    @Test
    @DisplayName("Travel이 DB에 저장이 잘 되는지 확인")
    void saveTravel() {

        // given
        Travel travel = new Travel(1L, "대구여행", State.ACTIVE, "서울", "대구", LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), new Member(), new ArrayList<>(), new ArrayList<>());

        // when
        Travel savedTravel = travelRepository.save(travel);

        // then
        Assertions.assertThat(travel).usingRecursiveComparison().isEqualTo(savedTravel);
    }

    @Test
    @DisplayName("State가 ACTIVE인 Travel 데이터만 조회되는지 확인")
    void isTravelDataActive() {

        // given
        Travel travelData1 = new Travel(1L, "대구여행1", State.ACTIVE, "서울", "대구", LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), new Member(), new ArrayList<>(), new ArrayList<>());
        Travel travelData2 = new Travel(2L, "대구여행2", State.ACTIVE, "서울", "대구", LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), new Member(), new ArrayList<>(), new ArrayList<>());
        Travel travelData3 = new Travel(3L, "대구여행3", State.ACTIVE, "서울", "대구", LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>(), new Member(), new ArrayList<>(), new ArrayList<>());

        travelRepository.save(travelData1);
        travelRepository.save(travelData2);
        travelRepository.save(travelData3);

        // when
        List<Travel> travelActive = travelRepository.getAllTravelActive();

        // then
        Assertions.assertThat(travelActive)
                .isNotEmpty() // travelActive 리스트가 비어 있지 않음을 확인
                .allMatch(travel -> travel.getState() == State.ACTIVE);
    }
}
