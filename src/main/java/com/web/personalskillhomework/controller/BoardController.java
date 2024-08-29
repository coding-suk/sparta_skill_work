package com.web.personalskillhomework.controller;

import com.web.personalskillhomework.dto.board.request.BoardSaveRequestDto;
import com.web.personalskillhomework.dto.board.request.BoardUpdateRequestDto;
import com.web.personalskillhomework.dto.board.response.BoardSaveResponseDto;
import com.web.personalskillhomework.dto.board.response.BoardSimpleResponseDto;
import com.web.personalskillhomework.dto.board.response.BoardUpdateResponseDto;
import com.web.personalskillhomework.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardSaveResponseDto> saveBoard(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        return ResponseEntity.ok(boardService.saveBoard(boardSaveRequestDto));
    }

    @GetMapping("/boards")
    public ResponseEntity<Page<BoardSimpleResponseDto>> getBoard(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(boardService.getBoards(page, size));
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardUpdateResponseDto> updateBoard(@PathVariable Long boardId, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return ResponseEntity.ok(boardService.updateBoard(boardId, boardUpdateRequestDto));
    }

    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }

}
