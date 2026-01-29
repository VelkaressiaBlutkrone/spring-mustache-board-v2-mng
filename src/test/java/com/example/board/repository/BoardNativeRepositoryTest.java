package com.example.board.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.board.entity.Board;

@Import(BoardNativeRepository.class) // Native Query를 사용하는 커스텀 리포지토리 등록
@DataJpaTest // JPA 테스트 환경 (트랜잭션 롤백 포함)
class BoardNativeRepositoryTest {
    @Autowired
    BoardNativeRepository repository;

    @Test
    // 1. 단건 조회 테스트 (Native Query)
    void findById_test() {
        int id = 1;

        // 내부적으로 "SELECT * FROM board_tb WHERE id = ?" 쿼리가 실행됨
        repository.findById(id);
    }

    @Test
    // 2. 전체 조회 테스트 (Native Query)
    void findAll_test() {
        // 내부적으로 "SELECT * FROM board_tb ORDER BY id DESC" 등의 쿼리가 실행됨
        List<Board> list = repository.findAll();
        for (Board board : list) {
            System.out.println(board);
        }
    }

    @Test
    // 3. 저장 테스트 (INSERT SQL 직접 실행)
    void save_test() {
        // 엔티티 객체가 아닌 파라미터를 받아 "INSERT INTO ..." 쿼리 실행
        // 리턴값은 영향받은 행(Row)의 개수 (성공 시 1)
        int result = repository.save("title7", "content7");

        assertThat(result).isEqualTo(1);
    }

    @Test
    // 4. 수정 테스트 (UPDATE SQL 직접 실행)
    void updateById_test() {
        // JPA의 Dirty Checking(변경 감지)을 사용하지 않고, 직접 "UPDATE ..." 쿼리 실행
        // id가 "6"인 게시글이 존재해야 성공 (테스트 데이터 상황에 따라 실패할 수도 있음)
        int result = repository.updateById("6", "title7->8", "content7->8");
        assertThat(result).isEqualTo(1);
    }

    @Test
    // 5. 삭제 테스트 (DELETE SQL 직접 실행)
    void deleteById_test() {
        String id = "3";
        // 내부적으로 "DELETE FROM board_tb WHERE id = ?" 쿼리 실행
        int result = repository.deleteById(id);
        assertThat(result).isEqualTo(1);
    }

}
