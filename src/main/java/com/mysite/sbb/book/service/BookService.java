package com.mysite.sbb.book.service;


import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 목록 + 페이징
    public Page<Book> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("created")));
        return bookRepository.findAll(pageable);
    }

    // 상세 조회
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 도서가 없습니다."));
    }


    // 등록
    public void createBook(BookDto bookDto) {
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .content(bookDto.getContent())
                .created(LocalDateTime.now())
                .build();
        bookRepository.save(book);
    }

    // 수정
    public void modifyBook(Book book, BookDto bookDto) {
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setContent(bookDto.getContent());
        bookRepository.save(book);
    }

    // 삭제
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }


}
