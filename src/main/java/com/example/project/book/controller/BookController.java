package com.example.project.book.controller;

import com.example.project.book.dto.BookFindAllResponse;
import com.example.project.book.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "도서 정보", description = "도서 정보 API")
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @Tag(name = "도서 전체 조회", description = "도서 전체 조회 API")
    @GetMapping("/books")
    private ResponseEntity<BookFindAllResponse> findAllBooks(int page, int size) {
        return ResponseEntity.ok().body(bookService.findAllBooks(page, size));
    }
}
