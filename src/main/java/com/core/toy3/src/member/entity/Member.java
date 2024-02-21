package com.core.toy3.src.member.entity;

import com.core.toy3.common.entity.BaseEntity;
import com.core.toy3.src.like.entity.UserLike;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity(name="member")
public class Member extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;  // email
  private String password;
  private String name;

  //Like 관계 및 여러 개의 좋아요를 가질 수 있음
  @OneToMany(mappedBy = "member")
  private final List<UserLike> likes = new ArrayList<>();
}
