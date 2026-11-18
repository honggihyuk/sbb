package com.mysite.sbb.answer.entity;

import com.mysite.sbb.audit.BaseEntity;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.question.entity.Question;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "answerList")
// @Table(name = "ABCD") DB에서 테이블명 변경
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

//    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;
}
