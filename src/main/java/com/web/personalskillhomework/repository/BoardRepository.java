package com.web.personalskillhomework.repository;

import com.web.personalskillhomework.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long > {
    Page<Board> findAllByOrderByModifiedAtDesc(Pageable pageAble);
}
