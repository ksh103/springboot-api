package com.example.project.book.dto.Response;

import com.example.project.book.domain.Author;
import com.example.project.book.domain.Book;
import com.example.project.book.domain.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class BookAddResponse {

    private Long bookId;

    public static BookAddResponse fromEntity(final Book book) {
        return BookAddResponse.builder()
                .bookId(book.getBookId())
                .build();
    }
}
