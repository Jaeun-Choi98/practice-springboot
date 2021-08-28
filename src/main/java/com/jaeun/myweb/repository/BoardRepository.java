package com.jaeun.myweb.repository;

import com.jaeun.myweb.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);
    //content나 title이 들어가는 객체를 찾아줌...
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    void deleteById(Long id);
}
