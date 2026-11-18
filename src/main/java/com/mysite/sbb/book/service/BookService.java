package com.mysite.sbb.book.service;

import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 목록 + 페이징
    public Page<Book> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("created")));
        Page<Book> paging = bookRepository.findAll(pageable);;
        return paging;
    }

    // 상세 조회
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 도서가 없습니다."));
    }

    // 조회수 증가
    public void increaseViewCount(Book book) {
        book.setViewCount(book.getViewCount() + 1);
        bookRepository.save(book);
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
