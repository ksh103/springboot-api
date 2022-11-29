package com.example.project.book.dto.Request;

import com.example.project.book.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookAddRequest {
    private String bookName;
    private Long publisherId;
    private Long authorId;

    @Builder
    public BookAddRequest(final String bookName, final Long publisherId, final Long authorId) {
        this.bookName = bookName;
        this.publisherId = publisherId;
        this.authorId = authorId;
    }
}
