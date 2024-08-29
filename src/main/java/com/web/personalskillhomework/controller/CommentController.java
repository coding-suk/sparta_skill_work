package com.web.personalskillhomework.controller;

import com.web.personalskillhomework.dto.comment.request.CommentSaveRequestDto;
import com.web.personalskillhomework.dto.comment.response.CommentResponseDto;
import com.web.personalskillhomework.dto.comment.response.CommentSaveResponseDto;
import com.web.personalskillhomework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public CommentSaveResponseDto saveComment(@PathVariable Long boardId, @RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        return commentService.saveComment(boardId, commentSaveRequestDto);
    }

    @GetMapping("/comments")
    public List<CommentResponseDto> getComment() {
        return commentService.getComment();
    }

}
