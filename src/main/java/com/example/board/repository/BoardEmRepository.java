package com.example.board.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.board.entity.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 붙어 있는 모든 필드를 초기화하는 생성자를 만들어줌.
@Repository
public class BoardEmRepository {

    private final EntityManager em;

    public Board findById(int id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b", Board.class);

        return (List<Board>) query.getResultList();
    }

    public void findAll2() {
        Query query1 = em.createQuery("select b.title, b.content from Board b");
        List<Board> list = query1.getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }

    public void delete(Board board) {
        em.remove(board);
    }

}
