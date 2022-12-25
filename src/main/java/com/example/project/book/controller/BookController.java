package com.example.project.book.controller;

import com.example.project.book.dto.Request.BookAddRequest;
import com.example.project.book.dto.Request.BookModifyRequest;
import com.example.project.book.dto.Response.BookResponse;
import com.example.project.book.dto.Response.BookFindAllResponse;
import com.example.project.book.dto.Response.BookFindResponse;
import com.example.project.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "도서 정보", description = "도서 정보 API")
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

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
    private ResponseEntity<BookResponse> addBook(@RequestBody BookAddRequest bookAddRequest) {
        log.info("addBook - Call");

        BookResponse bookAddResponse = bookService.addBook(bookAddRequest);

        return ResponseEntity
                .created(URI.create("/api/v1/books/" + bookAddResponse.getBookId()))
                .build();
    }

    @Tag(name = "도서 정보")
    @Operation(summary = "도서 정보 수정", description = "도서 정보를 수정한다.")
    @PutMapping("/{bookId}")
    private ResponseEntity<BookResponse> modifyBook(@Parameter(description = "도서 ID", required = true) @PathVariable Long bookId, @RequestBody BookModifyRequest bookModifyRequest) {
        log.info("modifyBook - Call");

        Long bookModifyId = bookService.modifyBook(bookId, bookModifyRequest);

        return ResponseEntity
                .created(URI.create("/api/v1/books" + bookModifyId))
                .build();
    }


    @Tag(name = "도서 정보")
    @Operation(summary = "도서 정보 삭제", description = "도서 정보를 삭제한다.")
    @DeleteMapping("/{bookId}")
    private ResponseEntity<Void> deleteBook(@Parameter(description = "도서 ID", required = true) @PathVariable Long bookId){
        log.info("deleteBook - Call");

        bookService.deleteBook(bookId);

        return ResponseEntity.noContent().build();
    }
}
