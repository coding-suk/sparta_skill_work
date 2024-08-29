package com.web.personalskillhomework.service;

import com.web.personalskillhomework.dto.board.request.BoardSaveRequestDto;
import com.web.personalskillhomework.dto.board.request.BoardUpdateRequestDto;
import com.web.personalskillhomework.dto.board.response.BoardSaveResponseDto;
import com.web.personalskillhomework.dto.board.response.BoardSimpleResponseDto;
import com.web.personalskillhomework.dto.board.response.BoardUpdateResponseDto;
import com.web.personalskillhomework.dto.comment.response.CommentResponseDto;
import com.web.personalskillhomework.entity.Board;
import com.web.personalskillhomework.entity.Comment;
import com.web.personalskillhomework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto boardSaveRequestDto) {


        Board newBoard = new Board(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContents());
        Board savedBoard = boardRepository.save(newBoard);

        return new BoardSaveResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContents());
    }

    public Page<BoardSimpleResponseDto> getBoards(int page, int size) {
        Pageable pageAble= PageRequest.of(page -1, size);

        List<Board> boardList = boardRepository.findAll();

        List<BoardSimpleResponseDto> dtoList = new ArrayList<>();

        Page<Board> boards = boardRepository.findAllByOrderByModifiedAtDesc(pageAble);

        return boards.map(board -> new BoardSimpleResponseDto(
                board.getId(),
                board.getTitle(),
                board.getComments()
        ));
//        for (Board board : boardList) {
//            List<Comment> commentList = board.getComments();
//            List<CommentResponseDto> commentDtoList = new ArrayList<>();
//
//            for(Comment comment : commentList) {
//                commentDtoList.add(new CommentResponseDto(comment.getId(), comment.getContents()));
//            }
//
//            BoardSimpleResponseDto dto = new BoardSimpleResponseDto(
//                    board.getId(),
//                    board.getTitle(),
//                    commentDtoList
//            );
//            dtoList.add(dto);
//        }
//        return dtoList;

    }

    @Transactional
    public BoardUpdateResponseDto updateBoard(Long boardId, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("보드 없음"));
        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContents());

        return new BoardUpdateResponseDto(board.getId(),board.getTitle(), board.getContents());
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        if( boardRepository.existsById(boardId)) {
            throw new NullPointerException("보드 없어요");
        }

        boardRepository.deleteById(boardId);
    }
}

