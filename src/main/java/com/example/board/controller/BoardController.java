package com.example.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.board.dto.BoardRequestDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

    // BoardService를 주입받아 게시글 관련 비즈니스 로직을 수행
    final BoardService boardService;

    /**
     * 게시글 목록 조회 (메인 페이지)
     * [GET] /
     * - 전체 게시글(List<Board>)을 조회해서 request scope에 "models"로 저장 후 index.mustache 뷰에
     * 전달
     * - 화면에서 게시글 리스트를 렌더링
     */
    @GetMapping("/")
    public String board(HttpServletRequest req) {
        // 모든 게시글 조회
        List<Board> models = boardService.list();
        // request의 "models"에 리스트 저장
        req.setAttribute("models", models);
        // index.mustache(템플릿) 반환
        return "index";
    }

    /**
     * 게시글 상세조회
     * [GET] /board/detail/{id}
     * - PathVariable로 전달된 id 값에 해당하는 게시글(Board)을 조회해서 request scope "model" 저장
     * - board/detail.mustache 화면에서 게시글 상세 정보 렌더링
     *
     * @param id 게시글 PK값
     */
    @GetMapping("/board/detail/{id}")
    public String detail(@PathVariable("id") String id, HttpServletRequest req) {
        // 게시글 id로 단일 게시글 조회
        Board model = boardService.board(id).orElse(null);
        // request의 "model"에 Board 객체 저장
        req.setAttribute("model", model);
        // board/detail.mustache 반환
        return "board/detail";
    }

    /**
     * 게시글 작성 폼 이동
     * [GET] /board/save-form
     * - 게시글 작성 페이지로 이동
     */
    @GetMapping("/board/save-form")
    public String saveForm() {
        // board/save-form.mustache 렌더링
        return "board/save-form";
    }

    /**
     * 게시글 수정 폼 이동
     * [GET] /board/update-form/{id}
     * - PathVariable로 전달된 id의 게시글(Board)를 조회하여 request scope "model" 저장
     * - board/update-form.mustache 뷰 렌더링
     */
    @GetMapping("/board/update-form/{id}")
    public String updateForm(@PathVariable("id") String id, HttpServletRequest req) {
        // id로 게시글 조회
        Board model = boardService.board(id).orElse(null);
        // request의 "model"에 Board 객체 저장
        req.setAttribute("model", model);
        // board/update-form.mustache 반환
        return "board/update-form";
    }

    /**
     * 게시글 등록 처리
     * [POST] /board/insert
     * - 사용자가 작성한 게시글 폼 데이터를 받아 게시글을 저장
     * - 저장 후 메인 페이지("/")로 리다이렉트
     *
     * @param dto 게시글 작성 데이터 (title, content 등)
     */
    @PostMapping("/board/insert")
    public String inset(@Valid BoardRequestDto dto) {
        // 게시글 저장 처리
        boardService.save(dto);
        // 저장 후 메인 페이지로 리다이렉트
        return "redirect:/";
    }

    /**
     * 게시글 수정 처리
     * [POST] /board/{id}/update
     * - 수정폼에서 전달된 게시글 데이터를 받아 해당 id의 게시글을 수정
     * - 수정 후 상세페이지로 리다이렉트
     *
     * @param id  게시글 PK
     * @param dto 게시글 수정 데이터
     */
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") String id, @Valid BoardRequestDto dto) {
        // BoardRequestDto에 id 세팅
        dto.setId(id);
        // 게시글 저장(수정)
        boardService.save(dto);
        // 수정 후 상세 페이지로 리다이렉트
        return "redirect:/board/detail/" + id;
    }

    /**
     * 게시글 삭제 처리
     * [GET] /board/{id}/delete
     * - 전달된 id의 게시글을 삭제
     * - 삭제 후 메인 페이지("/")로 리다이렉트
     *
     * @param id 삭제할 게시글 PK
     */
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") String id, HttpServletRequest req) {
        // 게시글 삭제 처리
        boardService.delete(id);
        // 메인 페이지로 리다이렉트
        return "redirect:/";
    }
}
