package com.example.project.book.dto;

import com.example.project.book.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
        return new BookFindResponse(
                book.getBookId(),
                book.getBookName(),
                book.getAuthor().getAuthorName(),
                book.getPublisher().getPublisherName());
    }
}
