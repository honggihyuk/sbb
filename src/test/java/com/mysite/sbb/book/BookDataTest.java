// src/test/java/com/mysite/sbb/book/BookDataTest.java
package com.mysite.sbb.book;

import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookDataTest {

    @Autowired
    private BookService bookService;

    @Test
    void createBooks() {
        for (int i = 1; i <= 120; i++) {
            BookDto dto = BookDto.builder()
                    .title("자바 책 " + i)
                    .author("저자" + (i % 3))
                    .build();
            bookService.createBook(dto);
        }
    }
}