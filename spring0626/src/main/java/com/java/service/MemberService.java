package com.java.service;

import com.java.www.dto.BoardDto;

public interface BoardService {

    // 게시글 전체 가져오기

    // 게시글 1개 가져오기
    BoardDto selectOne(int bno);

    // 게시글 1개 저장하기

}