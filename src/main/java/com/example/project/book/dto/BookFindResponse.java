package com.example.project.book.dto;

import com.example.project.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class BookFindResponse {

    private Long bookId;
    private String bookName;
    private String authorName;
    private String publisherName;

    public static BookFindResponse from (final Book book) {
        return BookFindResponse.builder()
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .authorName(book.getAuthor().getAuthorName())
                .publisherName(book.getPublisher().getPublisherName())
                .build();
    }
}
