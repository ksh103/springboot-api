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

    private String bookName;
    private String authorName;
    private String publisherName;

    public static BookAddResponse from(final Book book) {
        return BookAddResponse.builder()
                .bookName(book.getBookName())
                .authorName(book.getAuthor().getAuthorName())
                .publisherName(book.getPublisher().getPublisherName())
                .build();
    }
}
