package com.study.newBoard.repository;

import com.study.newBoard.entity.Boarda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardaRepository extends JpaRepository<Boarda, Integer> {
    
    //라이크 검색과 페이징
    //findBy(컬럼이름)Containing
    Page<Boarda> findByTitleContaining(String searchKeyword, Pageable pageable);
}
