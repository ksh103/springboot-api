package com.example.project.book.dto;

import com.example.project.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class BookFindResponse {

    private Long bookId;
    private String bookName;
    private String authorName;
    private String publisherName;

    private BookFindResponse(final Long bookId,
                             final String bookName,
                             final String authorName,
                             final String publisherName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisherName = publisherName;
    }

    public static BookFindResponse from (final Book book) {
        return BookFindResponse.builder()
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .authorName(book.getAuthor().getAuthorName())
                .publisherName(book.getPublisher().getPublisherName())
                .build();
    }
}
