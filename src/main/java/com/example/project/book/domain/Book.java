package com.example.project.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public void setBookName(final String bookName) {
        this.bookName = bookName;
    }

    @Builder
    public Book(final String bookName, final Publisher publisher, final Author author) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.author = author;
    }

    public void Modify(final String bookName) {
        this.bookName = bookName;
    }
}
