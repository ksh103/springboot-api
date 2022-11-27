package com.example.project.book.service;

import com.example.project.book.domain.Book;
import com.example.project.book.dto.BookFindAllResponse;
import com.example.project.book.repository.AuthorRepository;
import com.example.project.book.repository.BookRepository;
import com.example.project.book.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(final BookRepository bookRepository,
                       final AuthorRepository authorRepository,
                       final PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public BookFindAllResponse findAllBooks(int page, int size) {
        Page<Book> findBooks = bookRepository.findAll(PageRequest.of(page - 1, size, Sort.Direction.ASC, "bookId"));

        return BookFindAllResponse.from(findBooks);
    }
}
