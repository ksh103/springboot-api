package com.example.project.book.dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookModifyRequest {
    private String bookName;
}
