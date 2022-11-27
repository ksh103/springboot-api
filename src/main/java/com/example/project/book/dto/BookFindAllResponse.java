package com.example.project.book.dto;

import com.example.project.book.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BookFindAllResponse {

    private List<BookFindResponse> books;

    private BookFindAllResponse(final List<BookFindResponse> books) {
        this.books = books;
    }

    public static BookFindAllResponse from(final Page<Book> findBooks) {
        List<BookFindResponse> bookFindResponses = findBooks.stream()
                .map(BookFindResponse::from)
                .collect(Collectors.toList());

        return new BookFindAllResponse(bookFindResponses);
    }
}
