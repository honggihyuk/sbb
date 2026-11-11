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

    @Id     // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title; // 도서 제목

    @Column
    private String author; //저자

    @Column(columnDefinition = "TEXT")
    private String content; // 도서 내용

    private LocalDateTime created; // 생성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

}
