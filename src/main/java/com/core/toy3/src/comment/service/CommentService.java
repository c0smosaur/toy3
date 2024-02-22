package com.core.toy3.src.comment.service;

import com.core.toy3.src.comment.model.request.CommentRequest;
import com.core.toy3.src.comment.repository.CommentRepository;
import com.core.toy3.src.member.entity.MemberAdapter;
import com.core.toy3.src.member.repository.MemberRepository;
import com.core.toy3.src.trip.repository.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final TripRepository tripRepository;

    public void createComment(int travelId, CommentRequest request, MemberAdapter memberAdapter) {
    }

}
