package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.dto.AnswerDto;
import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

//    @Autowired
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page);
        log.info("========> paging : {}", paging);
//        System.out.println("======> " + list);
        model.addAttribute("paging", paging);
        return "question/list";

    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         AnswerDto answerDto,
                         Model model) {
        log.info("========> id : {}", id);
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question/detail";
    }

    @GetMapping("/create")
    public String createInputForm(QuestionDto questionDto) {
        return "question/inputForm";
    }

//    @PostMapping("/create")
//    public String createQuestion(@RequestParam(value = "subject") String subject,
//                                 @RequestParam(value = "content") String content) {
//
//
//        questionService.create(subject, content)

    @PostMapping("/create")
    public String createQuestion(@Valid QuestionDto questionDto,
                                 BindingResult bindingResult) {
        log.info("======> questionDto : {}", questionDto);
        if(bindingResult.hasErrors()) {
            return "question/inputForm";
        }
        questionService.createQuestion(questionDto);


        return "redirect:/question/list";
    }

}
