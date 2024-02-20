package com.core.toy3.src.like.repository;


import com.core.toy3.src.like.entity.Like;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    Like findByMemberAndTravel(Member member, Travel travel);
}
