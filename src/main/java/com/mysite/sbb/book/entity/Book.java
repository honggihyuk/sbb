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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;   //도서 제목

    @Column(length = 50, nullable = false)
    private String author;  //저자

    @Column(columnDefinition = "TEXT")
    private String content;  //소개

    private Double rating;

    private LocalDateTime created;  //등록일

    @Builder.Default
    private Integer viewCount = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviewList;

}
