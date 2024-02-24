package com.core.toy3.src.comment.service;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.src.comment.entity.Comment;
import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.repository.CommentRepository;
import com.core.toy3.src.member.entity.AuthMember;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.repository.MemberRepository;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.travel.repository.TravelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.core.toy3.common.response.ResponseStatus.TRAVEL_DOES_NOT_EXIST;
import static com.core.toy3.common.response.ResponseStatus.USER_DOES_NOT_EXIST;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final TravelRepository travelRepository;
    private final MemberRepository memberRepository;

    public Comment createComment(
            AuthMember member,
            CommentRequest commentRequest) {

        Member memberId = member.getMember();

        Member findMember = memberRepository.findById(memberId.getId())
                .orElseThrow(() -> new CustomException(USER_DOES_NOT_EXIST));

        Travel travel = travelRepository.findById(commentRequest.getTravelId())
                .orElseThrow(() -> new CustomException(TRAVEL_DOES_NOT_EXIST));

        Comment comment = Comment.builder()
                .member(findMember)
                .travel(travel)
                .content(commentRequest.getContent())
                .build();

        return commentRepository.save(comment);
    }

    public List<Comment> selectAllCommentByMe(AuthMember member) {
        return commentRepository.findAllByMemberId(member.getId());
    }
}
