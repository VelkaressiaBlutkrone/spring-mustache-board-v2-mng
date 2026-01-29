package com.example.board.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.board.entity.Board;

import jakarta.persistence.EntityManager;

@Import(BoardEmRepository.class) // 직접 구현한 Repository 클래스를 빈으로 등록
@DataJpaTest // JPA 테스트 전용 어노테이션 (인메모리 DB 사용, 트랜잭션 자동 롤백)
class BoardEmRepositoryTest {
    @Autowired
    BoardEmRepository repository;

    @Autowired
    EntityManager em;

    @Test
    // 1. 단건 조회 테스트
    void findById_test() {
        // given
        int id = 1;

        // when
        Board baord1 = repository.findById(id);
        Board baord2 = repository.findById(2);

        // then
        System.out.println(baord1);
        System.out.println(baord2);
    }

    @Test
    // 2. 전체 조회 테스트 (JPQL)
    void findAll_test() {
        // given

        // when
        List<Board> boards = repository.findAll();

        // then
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @Test
    // 3. 특정 컬럼만 조회하는 테스트 (Projection)
    void findAll_test2() {
        // when
        repository.findAll2();
    }

    @Test
    // 4. 삭제 테스트
    void delete_test() {
        // given
        Board board = repository.findById(1);

        // when
        repository.delete(board); // 영속성 컨텍스트에 삭제 요청 (상태 변경)
        em.flush(); // 실제 DB에 DELETE SQL 전송
    }

    @Test
    // 5. 수정 테스트 (Dirty Checking - 변경 감지)
    void update_test() {
        // given
        Board board = repository.findById(1); // 영속 상태가 됨

        // when
        board.setTitle("title-update"); // 객체의 값만 변경 (별도의 update 메서드 호출 X)
        em.flush(); // 트랜잭션 커밋 혹은 flush 시점에 변경 사항을 감지하여 UPDATE SQL 자동 전송

        // then
        List<Board> boards = repository.findAll();

        for (Board b : boards) {
            System.out.println(b);
        }
    }

    @Test
    // 6. 1차 캐시 동작 확인 테스트
    void findByIdv2_test() {
        int id = 1;

        repository.findById(id); // 1. DB 조회 -> 1차 캐시에 저장 (SQL 실행 O)

        em.clear(); // 2. 영속성 컨텍스트 초기화 (1차 캐시 비움)

        repository.findById(id); // 3. 캐시가 비었으므로 다시 DB 조회 (SQL 실행 O)

    }

}
