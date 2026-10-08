package com.mysite.sbb.answer.entity;

import com.mysite.sbb.question.entity.Question;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "answerList")
// @Table(name = "ABCD") DB에서 테이블명 변경
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

}
