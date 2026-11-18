package com.mysite.sbb.book.service;

import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.dto.ReviewDto;
import com.mysite.sbb.book.entity.Review;
import com.mysite.sbb.book.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void createdReview(Book book, ReviewDto reviewDto) {
        Review review = Review.builder()
                .book(book)
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .created(LocalDateTime.now())
                .build();
        reviewRepository.save(review);
    }

    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 없습니다."));
    }

    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }
}
