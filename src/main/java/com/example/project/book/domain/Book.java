package com.example.project.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @OneToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @OneToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Builder
    public Book(String bookName, Publisher publisher, Author author) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
    }
}
