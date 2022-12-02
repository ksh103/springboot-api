package com.example.project.book.service;

import com.example.project.book.domain.Author;
import com.example.project.book.domain.Book;
import com.example.project.book.domain.Publisher;
import com.example.project.book.dto.Request.BookAddRequest;
import com.example.project.book.dto.Request.BookModifyRequest;
import com.example.project.book.dto.Response.BookResponse;
import com.example.project.book.dto.Response.BookFindAllResponse;
import com.example.project.book.dto.Response.BookFindResponse;
import com.example.project.book.repository.AuthorRepository;
import com.example.project.book.repository.BookRepository;
import com.example.project.book.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Transactional(readOnly = true)
    public BookFindAllResponse findAllBooks(int page, int size) {
        Page<Book> findBooks = bookRepository.findAll(PageRequest.of(page - 1, size, Sort.Direction.ASC, "bookId"));

        return BookFindAllResponse.fromEntity(findBooks);
    }

    @Transactional(readOnly = true)
    public BookFindResponse findBook(final Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("찾는 도서가 없습니다."));

        return BookFindResponse.fromEntity(book);
    }

    // 추가적으로 고려해볼 로직 1) 이미 등단된 작가라면? -> 작가 검색 기능 추가
    // 추가적으로 고려해볼 로직 2) 작가 검색 기능 추가 후 -> 등단 되어 있지 않은 작가라면 작가 추가
    public BookResponse addBook(final BookAddRequest bookAddRequest) {
        // 출판사, 작가 정보 있는 지 확인
        Publisher publisher = publisherIsCheck(bookAddRequest.getPublisherId());
        Author author = authorIsCheck(bookAddRequest.getAuthorId());

        // 도서 정보에 책 제목, 출판사 정보, 작가 정보 저장
        Book book = Book.builder()
                .bookName(bookAddRequest.getBookName())
                .publisher(publisher)
                .author(author)
                .build();

        Book saveBook = bookRepository.save(book);

        return BookResponse.fromEntity(saveBook);
    }

    @Transactional(readOnly = true)
    public Publisher publisherIsCheck(final Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException("찾는 출판사가 없습니다."));

        return publisher;
    }

    @Transactional(readOnly = true)
    public Author authorIsCheck(final Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("찾는 작가가 없습니다."));

        return author;
    }
}
