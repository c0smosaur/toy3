package com.core.toy3.src.comment.service;

import com.core.toy3.common.exception.CustomException;
import com.core.toy3.common.response.Response;
import com.core.toy3.src.comment.entity.Comment;
import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.model.response.CommentResponse;
import com.core.toy3.src.comment.repository.CommentRepository;
import com.core.toy3.src.member.entity.Member;
import com.core.toy3.src.member.repository.MemberRepository;
import com.core.toy3.src.travel.entity.Travel;
import com.core.toy3.src.travel.repository.TravelRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.core.toy3.common.response.ResponseStatus.TRAVEL_DOES_NOT_EXIST;
import static com.core.toy3.common.response.ResponseStatus.USER_DOES_NOT_EXIST;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final TravelRepository travelRepository;
    private final HttpSession httpSession;

//    public CommentService()

//
//    public CommentService(CommentRepository commentRepository, TravelRepository travelRepository, MemberRepository memberRepository, MemberAdapter memberAdapter) {
//        this.commentRepository = commentRepository;
//        this.travelRepository = travelRepository;
//        this.memberRepository = memberRepository;
//        this.memberAdapter = memberAdapter;
//    }

    public Response<CommentResponse> createComment(Long travelId, CommentRequest request) {

//        Long memberId = 1L;

        Member member = (Member) httpSession.getAttribute("loggedInMember");

        /*if (member == null){
            throw new CustomException(USER_DOES_NOT_EXIST);
        }*/

        /*Travel travel = travelRepository.findById(request.getTravelId())
                .orElseThrow(() -> new CustomException(TRAVEL_DOES_NOT_EXIST));*/

        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new CustomException(TRAVEL_DOES_NOT_EXIST));

        Comment comment = Comment.of(member,travel,request.getContent());
        Comment saveComment = commentRepository.save(comment);

        CommentResponse response = CommentResponse.fromEntity(saveComment);
        return Response.response(response);


//        commentRepository.save(Comment.createComment(member.getMember(), travel, request.getContent()));
//        return Response.response(CommentResponse.fromEntity(Comment.of(MemberAdapter.getMember(),travel,request.getContent())));

    }

}
