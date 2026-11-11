package com.mysite.sbb.book.controller;

import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.dto.ReviewDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.service.BookService;
import groovy.util.logging.Slf4j;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 목록: /book/list?page=0&kw=자바
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Book> paging = bookService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "book/list";
    }

    // 등록 폼
    @GetMapping("/create")
    public String createForm(BookDto bookDto) {
        return "book/inputForm";
    }

    // 등록 처리
    @PostMapping("/create")
    public String create(@Valid BookDto bookDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "book/inputForm";
        }
        bookService.createBook(bookDto);
        return "redirect:/book/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id) {
        return "book/detail";
    }

    // 수정 폼 (기존 값 채워서 보여주기)
    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable("id") Long id, BookDto bookDto) {
        Book book = bookService.getBook(id);
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setContent(book.getContent());
        return "book/inputForm";
    }

    // 수정 처리
    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id,
                         @Valid BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/inputForm";
        }
        Book book = bookService.getBook(id);
        bookService.modifyBook(book, bookDto);
        return "redirect:/book/" + id;
    }

    // 삭제 처리
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Book book = bookService.getBook(id);
        bookService.deleteBook(book);
        return "redirect:/book/list";
    }
}
