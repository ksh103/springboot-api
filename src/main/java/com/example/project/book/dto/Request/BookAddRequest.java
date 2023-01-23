package com.example.project.book.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAddRequest {
    private String bookName;
    private Long publisherId;
    private Long authorId;
}
