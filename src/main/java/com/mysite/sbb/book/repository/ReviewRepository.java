package com.mysite.sbb.book.repository;

import com.mysite.sbb.book.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
