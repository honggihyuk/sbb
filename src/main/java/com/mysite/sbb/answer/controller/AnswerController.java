package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/answer/create/{id}")
    public String createAnswer(@PathVariable("id") Long id,
                               @RequestParam(value = "content") String content ) {
        log.info("=======> id : {}, content : {}", id, content); //Slf4j 롬복
        Question question = questionService.getQuestion(id);
        Answer answer = Answer.builder()
                .question(question)
                .content(content)
                .created(LocalDateTime.now())
                .build();

        answerService.createAnswer(answer);

        return "redirect:/question/" + question.getId();
    }
}
