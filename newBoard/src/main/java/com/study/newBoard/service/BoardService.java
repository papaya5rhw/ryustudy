package com.study.newBoard.service;

import com.study.newBoard.entity.Board;
import com.study.newBoard.entity.Boarda;
import com.study.newBoard.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;

@Service
public interface BoardService {

    public void write(Board board, MultipartFile file) throws Exception;

    public Page<Board> boardList(Pageable pageable);

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable);

    public Board boardView(Integer id);

    public void boardDelete(Integer id);

    public void write2(Boarda boarda) throws Exception;

    //mybatis xml
    public List<Map<String, String>> boardList2();


}
