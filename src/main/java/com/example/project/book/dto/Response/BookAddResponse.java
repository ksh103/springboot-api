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

    public static BookAddResponse from(final Book book) {
//        return BookAddResponse.builder()
//                .bookName(book.getBookName())
//                .authorName(book.getAuthor().getAuthorName())
//                .publisherName(book.getPublisher().getPublisherName())
//                .build();

        return BookAddResponse.builder()
                .bookId(book.getBookId())
                .build();
    }
}
