package com.example.project.book.dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookAddRequest {
    private String bookName;
    private Long publisherId;
    private String authorName;
}
