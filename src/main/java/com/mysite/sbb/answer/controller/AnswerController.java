package com.mysite.sbb.answer.controller;

import com.mysite.sbb.answer.dto.AnswerDto;
import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.service.AnswerService;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.service.MemberService;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberService memberService;

//    @PostMapping("/answer/create/{id}")
//    public String createAnswer(@PathVariable("id") Long id,
//                               @RequestParam(value = "content") String content ) {
//        log.info("=======> id : {}, content : {}", id, content); //Slf4j 롬복
//        Question question = questionService.getQuestion(id);
//        Answer answer = Answer.builder()
//                .question(question)
//                .content(content)
//                .created(LocalDateTime.now())
//                .build();
//
//        answerService.createAnswer(answer);
//
//        return "redirect:/question/" + question.getId();
//    }

    @PreAuthorize("isAuthenticated()") // 로그인 한 사용자만 접근 가능
    @PostMapping("/answer/create/{id}")
    public String createAnswer(@PathVariable("id") Long id,
                               @Valid AnswerDto answerDto,
                               BindingResult bindingResult,
                               Principal principal,
                               Model model) {
        log.info("=======> id : {}, answerDto : {}", id, answerDto); //Slf4j 롬복

        Question question = questionService.getQuestion(id);
        Member author = memberService.getMember(principal.getName());

        if(bindingResult.hasErrors()){
            model.addAttribute(question);
            return "question/detail";
        }



        answerService.createAnswer(question, answerDto, author);

        return "redirect:/question/" + question.getId();
    }
}
