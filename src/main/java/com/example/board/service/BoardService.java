package com.example.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dto.BoardRequestDto;
import com.example.board.entity.Board;
import com.example.board.mapper.BoardMapper;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;
    private final BoardRepository repository;

    public List<Board> list() {
        return repository.findAllByOrderByIdDesc();
    }

    public Optional<Board> board(String id) {
        return repository.findById(Long.parseLong(id));
    }

    @Transactional
    public void save(BoardRequestDto dto) {
        repository.save(mapper.toBoard(dto));
    }

    public void update(String id, BoardRequestDto dto) {
        Board board = repository.findById(Long.parseLong(id)).orElse(null);

        if (board != null) {
            board.setTitle(dto.getTitle());
        }
    }

    @Transactional
    public void delete(String id) {
        Board delItem = repository.findById(Long.parseLong(id)).orElse(null);

        if (delItem != null) {
            repository.delete(delItem);
        }

    }
}
