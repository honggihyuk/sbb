package com.mysite.sbb.book.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "reviewList")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id     // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title; // 도서 제목

    @Column
    private String author; //저자

    @Column(columnDefinition = "TEXT")
    private String content; // 감상평

    private LocalDateTime created; // 생성 날짜

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviewList;
}
