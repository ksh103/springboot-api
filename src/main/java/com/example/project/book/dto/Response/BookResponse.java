package com.example.project.book.dto.Response;

import com.example.project.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long bookId;

    public static BookResponse fromEntity(final Book book) {
        return BookResponse.builder()
                .bookId(book.getBookId())
                .build();
    }
}
