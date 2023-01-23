package com.example.project.book.controller;

import com.example.project.book.dto.Request.BookAddRequest;
import com.example.project.book.dto.Response.BookFindResponse;
import com.example.project.book.dto.Response.BookResponse;
import com.example.project.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    @Test
    @DisplayName("도서 정보 조회 성공")
    void book_read_success() throws Exception {

        BookFindResponse bookFindResponse = BookFindResponse.builder()
                .bookId(1L)
                .bookName("토비의 스프링3")
                .authorName("이일민")
                .publisherName("에이콘")
                .build();

        when(bookService.findBook(any()))
                .thenReturn(bookFindResponse);

        mockMvc.perform(get("/api/v1/books/1")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").exists())
                .andExpect(jsonPath("$.bookName").exists())
                .andExpect(jsonPath("$.authorName").exists())
                .andExpect(jsonPath("$.publisherName").exists());

    }

    @Test
    @DisplayName("pageable 파라미터 검증")
    void evaluates_pageable_parameter() throws Exception {

        mockMvc.perform(get("/api/v1/books")
                        .param("page", "0")
                        .param("size", "20")
                        .param("sort", "bookId.desc"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        verify(bookService).findAllBooks(pageableCaptor.capture());
        PageRequest pageRequest = (PageRequest) pageableCaptor.getValue();

        assertEquals(0, pageRequest.getPageNumber());
        assertEquals(20, pageRequest.getPageSize());
        assertEquals(Sort.by("bookId", "desc"), pageRequest.withSort(Sort.by("bookId", "desc")).getSort());
    }

    @Test
    @DisplayName("도서 정보 등록 성공")
    void book_post_success() throws Exception {

        BookAddRequest bookAddRequest = BookAddRequest.builder()
                .bookName("book_title")
                .authorId(1L)
                .publisherId(1L)
                .build();

        when(bookService.addBook(any()))
                .thenReturn(BookResponse.builder()
                        .bookId(1L)
                        .build());

        mockMvc.perform(post("/api/v1/books")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(bookAddRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.message").value("도서 정보 등록 성공"))
                .andExpect(jsonPath("$.bookId").value(0));
    }
}
