package com.example.board.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.example.board.dto.BoardRequestDto;
import com.example.board.entity.Board;

@Component
public class BoardMapper {
    public Board toBoard(BoardRequestDto dto) {
        return Board.builder()
                .id(dto.getId() != null ? Integer.parseInt(dto.getId()) : null)
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
