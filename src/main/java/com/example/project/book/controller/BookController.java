package com.example.project.book.controller;

import com.example.project.book.dto.Request.BookAddRequest;
import com.example.project.book.dto.Response.BookAddResponse;
import com.example.project.book.dto.Response.BookFindAllResponse;
import com.example.project.book.dto.Response.BookFindResponse;
import com.example.project.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "도서 정보", description = "도서 정보 API")
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @Tag(name = "도서 정보")
    @Operation(summary = "도서 전체 조회", description = "도서 전체 목록을 조회한다.")
    @GetMapping("")
    private ResponseEntity<BookFindAllResponse> findAllBooks(int page, int size) {
        log.info("findAllBooks - Call");

        return ResponseEntity.ok().body(bookService.findAllBooks(page, size));
    }

    @Tag(name = "도서 정보")
    @Operation(summary = "특정 도서 조회", description = "검색한 도서를 조회한다.")
    @GetMapping("/{bookId}")
    private ResponseEntity<BookFindResponse> findBook(@Parameter(description = "도서 ID", required = true) @PathVariable Long bookId) {
        log.info("findBook - Call");

        return ResponseEntity.ok().body(bookService.findBook(bookId));
    }

    @Tag(name = "도서 정보")
    @Operation(summary = "도서 정보 등록", description = "도서 정보를 등록한다.")
    @PostMapping("")
    private  ResponseEntity<BookAddResponse> addBook(@RequestBody BookAddRequest bookAddRequest) {
        log.info("addBook - Call");

        return ResponseEntity.ok().body(bookService.addBook(bookAddRequest));
    }
}
