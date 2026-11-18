package com.mysite.sbb.question.entity;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.audit.BaseEntity;
import com.mysite.sbb.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "answerList")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Question extends BaseEntity {

    @Id     // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "question_id") //DB에서 컬럼명 변경
    private Long id; // 아이디 (primary key)

    @Column(length = 100, nullable = false)
    private String subject; // 질문 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 질문 내용

//    private LocalDateTime created; // 생성 날짜

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;

}
