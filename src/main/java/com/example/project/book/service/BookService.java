package com.example.project.book.service;

import com.example.project.book.domain.Author;
import com.example.project.book.domain.Book;
import com.example.project.book.domain.Publisher;
import com.example.project.book.dto.Request.BookAddRequest;
import com.example.project.book.dto.Response.BookAddResponse;
import com.example.project.book.dto.Response.BookFindAllResponse;
import com.example.project.book.dto.Response.BookFindResponse;
import com.example.project.book.repository.AuthorRepository;
import com.example.project.book.repository.BookRepository;
import com.example.project.book.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
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

    @Transactional(readOnly = true)
    public BookFindAllResponse findAllBooks(int page, int size) {
        Page<Book> findBooks = bookRepository.findAll(PageRequest.of(page - 1, size, Sort.Direction.ASC, "bookId"));

        return BookFindAllResponse.from(findBooks);
    }

    @Transactional(readOnly = true)
    public BookFindResponse findBook(final Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("찾는 도서가 없습니다."));

        return BookFindResponse.from(book);
    }

    // 추가적으로 고려해볼 로직 1) 이미 등단된 작가라면? -> 작가 검색 기능 추가
    // 추가적으로 고려해볼 로직 2) 작가 검색 기능 추가 후 -> 등단 되어 있지 않은 작가라면 작가 추가
    public BookAddResponse addBook(final BookAddRequest bookAddRequest) {

        // 1. 다양하게 보여주기 위해 출판사의 경우 이미 등록된 출판사 목록 중 활용한다는 가정
        // 2. 작가의 경우 사용자가 직접 등록 -> DB에 반영되어야 할 것
        Optional<Publisher> publisher = publisherRepository.findById(bookAddRequest.getPublisherId());

        // 작가 먼저 등록
        Author author = Author.builder()
                .authorName(bookAddRequest.getAuthorName())
                .build();

        Author saveAuthor = authorRepository.save(author);

        // 도서 정보에 책 제목, 출판사 정보, 작가 정보 저장
        Book book = Book.builder()
                .bookName(bookAddRequest.getBookName())
                .publisher(publisher.get())
                .author(saveAuthor)
                .build();

        Book saveBook = bookRepository.save(book);

        return BookAddResponse.from(saveBook);
    }
}
