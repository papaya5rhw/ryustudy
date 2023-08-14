package com.study.newBoard.service;

import com.study.newBoard.entity.Board;
import com.study.newBoard.entity.Boarda;
import com.study.newBoard.repository.BoardRepository;
import com.study.newBoard.repository.BoardaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardaRepository boardaRepository;

    @Override
    public void write(Board board, MultipartFile file) throws Exception{

        //저장소
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        //고유한값
        UUID uuid = UUID.randomUUID();

        String fileNmae = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileNmae);

        file.transferTo(saveFile);

        board.setFileName(fileNmae);
        board.setFilePath("/files/" + fileNmae);
        Date dt = new Date();
        board.setRegDt(dt);

        boardRepository.save(board);
    }

    @Override
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    @Override
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    @Override
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    @Override
    public void write2(Boarda boarda) throws Exception{

        Date dt = new Date();
        boarda.setRegDt(dt);

        boardaRepository.save(boarda);

    }

}
