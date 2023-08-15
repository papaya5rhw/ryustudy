package com.study.newBoard.dao;

import com.study.newBoard.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {

    public List<Map<String, String>> boardList2();

    //@Select("select * from board where id = ${id}")
    //Map<String, String> getBoardInfo (@Param("id") String id);

}
