package com.web.personalskillhomework.service;

import com.web.personalskillhomework.dto.comment.request.CommentSaveRequestDto;
import com.web.personalskillhomework.dto.comment.response.CommentResponseDto;
import com.web.personalskillhomework.dto.comment.response.CommentSaveResponseDto;
import com.web.personalskillhomework.entity.Board;
import com.web.personalskillhomework.entity.Comment;
import com.web.personalskillhomework.repository.BoardRepository;
import com.web.personalskillhomework.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long boardId, CommentSaveRequestDto commentSaveRequestDto) {

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("보드 없어요"));

       Comment newComment = new Comment(commentSaveRequestDto.getContents(), board);
        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(savedComment.getId(), savedComment.getContents());
    }

    public List<CommentResponseDto> getComment() {
        List<Comment> commentList = commentRepository.findAll();

        List<CommentResponseDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            dtoList.add(new CommentResponseDto(comment.getId(), comment.getContents()));
        }
        return dtoList;
    }

}
