package com.mysite.sbb.book.controller;

import com.mysite.sbb.book.dto.ReviewDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.entity.Review;
import com.mysite.sbb.book.service.BookService;
import com.mysite.sbb.book.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final BookService bookService;
    private final ReviewService reviewService;

    // 리뷰 등록: /review/create/{도서id}
    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") Long id,
                         @Valid ReviewDto reviewDto, BindingResult bindingResult,
                         Model model) {
        Book book = bookService.getBook(id);
        if(bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "book/detail";
        }
        reviewService.createdReview(book, reviewDto);
        return "redirect:/book/" + id;
    }

    // 리뷰 삭제: /review/delete/{리뷰id}
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Review review = reviewService.getReview(id);
        Long bookId = review.getBook().getId();
        reviewService.deleteReview(review);
        return "redirect:/book/" + bookId;
    }
}
