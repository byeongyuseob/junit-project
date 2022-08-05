package com.example.junitproject.web.dto;

import com.example.junitproject.domain.Book;
import lombok.Setter;

@Setter // Controller에서 Setter가 호출되면서 DTO에 값이 채워진다.
public class BookSaveRequestDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
