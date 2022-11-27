package com.example.project.book.controller;

import com.example.project.book.dto.BookFindAllResponse;
import com.example.project.book.dto.BookFindResponse;
import com.example.project.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Tag(name = "도서 정보")
    @Operation(summary = "도서 전체 조회", description = "도서 전체 목록을 조회한다.")
    @GetMapping("/books")
    private ResponseEntity<BookFindAllResponse> findAllBooks(int page, int size) {
        log.info("findAllBooks - Call");

        return ResponseEntity.ok().body(bookService.findAllBooks(page, size));
    }

    @Tag(name = "도서 정보")
    @Operation(summary = "특정 도서 조회", description = "검색한 도서를 조회한다.")
    @GetMapping("/books/{bookId}")
    private ResponseEntity<BookFindResponse> findBooks(@Parameter(description = "도서 ID", required = true) @PathVariable Long bookId) {
        log.info("findBooks - Call");

        return ResponseEntity.ok().body(bookService.findBooks(bookId));
    }
}
