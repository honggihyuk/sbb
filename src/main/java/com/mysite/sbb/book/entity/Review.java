package com.mysite.sbb.book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "book")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;  //리뷰 내용

    private Integer rating;  // 평점 1~5점

    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;  // FK: book_id

}
