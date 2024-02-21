package com.core.toy3.src.travel.entity;

import com.core.toy3.common.constant.State;
import com.core.toy3.common.entity.BaseEntity;
import com.core.toy3.src.like.entity.Like;
import com.core.toy3.src.travel.model.request.TravelRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
//@Where(clause = "state = 'ACTIVE'") // 조회결과 제외가 아닌 일치하지 않는 데이터 조회시 예외 처리하도록 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Travel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String travelName;

    @Enumerated(EnumType.STRING)
    private State state;

    private String departure;
    private String arrival;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public static Travel fromRequest(TravelRequest travelRequest) {
        return Travel.builder()
                .travelName(travelRequest.getTravelName())
                .state(State.ACTIVE)
                .departure(travelRequest.getDeparture())
                .arrival(travelRequest.getArrival())
                .departureTime(travelRequest.getDepartureTime())
                .arrivalTime(travelRequest.getArrivalTime())
                .build();

    }

    public void update(TravelRequest travelRequest) {

        this.travelName = travelRequest.getTravelName();
        this.departure = travelRequest.getDeparture();
        this.arrival = travelRequest.getArrival();
        this.departureTime = travelRequest.getDepartureTime();
        this.arrivalTime = travelRequest.getArrivalTime();
    }



    @OneToMany(mappedBy = "travel")
    private List<Like> likes;

    public int getLikeCount() {
        return likes != null ? likes.size() : 0;
    }
}

