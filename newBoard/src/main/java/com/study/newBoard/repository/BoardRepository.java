package com.study.newBoard.repository;

import com.study.newBoard.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    
    //라이크 검색과 페이징
    //findBy(컬럼이름)Containing
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
