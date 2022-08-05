package com.example.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // DB와 관련된 컴포넌트들만 메모리에 로딩 (Unit test env)1
class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;

    // 1. 책 등록
    @Test
    public void 책등록_test(){
        // given (테이터 준비)
        String title = "junit5";
        String author = "meta-coding";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        // when (데이터 실행)
        Book bookPersistence = bookRepository.save(book);

        //then (검증)
        assertEquals(title, bookPersistence.getTitle());
        assertEquals(author, bookPersistence.getAuthor());
    }

    // 2. 책 목록보기

    // 3. 책 한 권 보기

    // 4. 책 수정

    // 5. 책 삭제

}