package com.example.junitproject.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // DB와 관련된 컴포넌트들만 메모리에 로딩 (Unit test env)1
class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;

    // @BeforeAll // 테스트 시작 전 한번만 실행
    @BeforeEach // 각 테스트 시작 전 실행
    public void 데이터준비(){
        String title = "junit";
        String author = "getInThere";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        Book bookPersistence = bookRepository.save(book);
    }

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
    @Test
    public void 책목록보기_test(){
        //given
        String title = "junit";
        String author = "getInThere";

        //when
        List<Book> booksPersistence = bookRepository.findAll();

        //then
        assertEquals(title, booksPersistence.get(0).getTitle());
        assertEquals(author, booksPersistence.get(0).getAuthor());
    }


    // 3. 책 한 권 보기
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책한권보기_test(){
        //given
        String title = "junit";
        String author = "getInThere";

        //when
        Book book = bookRepository.findById(1L).get();

        //then
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

    // 4. 책 수정

    // 5. 책 삭제
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test(){
        //given
        Long id = 1L;

        //when
        bookRepository.deleteById(id);

        //then
        //Optional<Book> bookPersistance = bookRepository.findById(id);
        assertFalse(bookRepository.findById(id).isPresent());
    }

}