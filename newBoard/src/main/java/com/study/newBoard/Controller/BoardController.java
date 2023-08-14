package com.study.newBoard.Controller;

import com.study.newBoard.entity.Board;
import com.study.newBoard.entity.Boarda;
import com.study.newBoard.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.SortDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;



@Controller
@Log4j2
public class BoardController {

    @Autowired
    private BoardService boardService;

    //등록 페이지
    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "boardwrite";
    }

    //등록
    @PostMapping("/board/writepro")
    public String boardwritepro(Board board, Model model, MultipartFile file)throws Exception {

        log.info("제목 : " + board.getTitle());
        log.info("내용 : " + board.getContent());

        try{
            boardService.write(board, file);

            model.addAttribute("message", "글 작성이 완료되었습니다.");
            model.addAttribute("url", "/board/list");
        }catch(Exception e){
            e.printStackTrace();

            model.addAttribute("message", "글 작성에 실패하였습니다.");
            model.addAttribute("url", "/board/list");
        }

        //return "redirect:/board/list";
        return "message";
    }

    //목록 페이지
    @GetMapping("/board/list")
    public String boardList(Model model
            , @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword){

        Page<Board> list = null;

        if(searchKeyword != null){
            list = boardService.boardSearchList(searchKeyword,pageable);
        }else{
            list = boardService.boardList(pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //0부터 시작하기 때문에
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    //상세 페이지
    @GetMapping("/board/view") //쿼리스트링
    public String boardView(Model model, Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    //삭제
    @GetMapping("/board/delete") //쿼리스트링
    public String boardDelete(Model model, Integer id){

        try{
            boardService.boardDelete(id);

            model.addAttribute("message", "글 삭제가 완료되었습니다.");
            model.addAttribute("url", "/board/list");
        }catch(Exception e){
            e.printStackTrace();

            model.addAttribute("message", "글 삭제에 실패하였습니다.");
            model.addAttribute("url", "/board/list");
        }

        //return "redirect:/board/list";
        return "message";

    }

    //수정 페이지
    @GetMapping("/board/modify/{id}") //패스베리어블로받기
    public String boardModify(@PathVariable("id") Integer id, Model model){

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";
    }

    //수정
    @PostMapping("board/update/{id}") //패스베리어블로받기
    public String boarUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file)throws Exception{

        try{
            Board boardTemp = boardService.boardView(id); //기존정보 읽어 오기
            boardTemp.setTitle(board.getTitle()); //덮어씌우기
            boardTemp.setContent(board.getContent()); //덮어씌우기

            boardService.write(boardTemp, file); //등록

            model.addAttribute("message", "글 수정이 완료되었습니다.");
            model.addAttribute("url", "/board/list");
        }catch(Exception e){
            e.printStackTrace();

            model.addAttribute("message", "글 수정에 실패하였습니다.");
            model.addAttribute("url", "/board/list");
        }

        //return "redirect:/board/list";
        return "message";
    }

    //등록 페이지2
    @GetMapping("/board/write2")
    public String boardWriteForm2(){

        return "boardwrite2";
    }

    //등록2
    @PostMapping("/board/writepro2")
    public String boardwritepro2(Boarda boarda, Model model)throws Exception {

        log.info("제목 : " + boarda.getTitle());
        log.info("내용 : " + boarda.getContent());

        try{
            boardService.write2(boarda);

            model.addAttribute("message", "글 작성이 완료되었습니다.");
            model.addAttribute("url", "/board/list");
        }catch(Exception e){
            e.printStackTrace();

            model.addAttribute("message", "글 작성에 실패하였습니다.");
            model.addAttribute("url", "/board/list");
        }

        //return "redirect:/board/list";
        return "message";
    }


}
